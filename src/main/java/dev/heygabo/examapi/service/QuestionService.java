package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.ChoiceRequest;
import dev.heygabo.examapi.dto.ChoiceResponse;
import dev.heygabo.examapi.dto.CreateQuestionRequest;
import dev.heygabo.examapi.dto.QuestionResponse;
import dev.heygabo.examapi.entity.Choice;
import dev.heygabo.examapi.entity.College;
import dev.heygabo.examapi.entity.Question;
import dev.heygabo.examapi.entity.Subject;
import dev.heygabo.examapi.entity.TextBlock;
import dev.heygabo.examapi.repository.CollegeRepository;
import dev.heygabo.examapi.repository.QuestionRepository;
import dev.heygabo.examapi.repository.SubjectRepository;
import dev.heygabo.examapi.repository.TextBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CollegeRepository collegeRepository;
    private final SubjectRepository subjectRepository;
    private final TextBlockRepository textBlockRepository;

    public Question getDailyQuestionForCategory(Long categoryId) {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();

        List<Question> candidates =
            questionRepository.findBySubject_Category_IdAndCreatedAtBefore(categoryId, startOfToday);

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No questions found for category " + categoryId);
        }

        candidates.sort(Comparator.comparing(Question::getId));

        long daysSinceEpoch = LocalDate.now().toEpochDay();
        long seed = daysSinceEpoch * 31 + categoryId;

        Random seededRandom = new Random(seed);
        int index = seededRandom.nextInt(candidates.size());

        return candidates.get(index);
    }


    public List<Question> getQuizQuestions(Long collegeId, Long subjectId, String examPeriod, int count) {
        List<Question> candidates =
            questionRepository.findByCollegeIdAndSubjectIdAndExamPeriod(collegeId, subjectId, examPeriod);

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No questions found for the given filters");
        }

        List<Question> shuffled = new ArrayList<>(candidates);
        Collections.shuffle(shuffled);

        int actualCount = Math.min(count, shuffled.size());
        return shuffled.subList(0, actualCount);
    }
    
    public QuestionResponse createQuestion(CreateQuestionRequest request) {

        long correctCount = request.getChoices().stream()
            .filter(ChoiceRequest::getIsCorrect)
            .count();

        if (correctCount != 1) {
            throw new IllegalArgumentException(
                "Exactly one choice must be marked correct, found " + correctCount
            );
        }

        College college = collegeRepository.findById(request.getCollegeId())
            .orElseThrow(() -> new IllegalArgumentException("College not found: " + request.getCollegeId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
            .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + request.getSubjectId()));

        TextBlock textBlock = null;
        if (request.getTextBlockId() != null) {
            textBlock = textBlockRepository.findById(request.getTextBlockId())
                .orElseThrow(() -> new IllegalArgumentException("Text block not found: " + request.getTextBlockId()));
        }

        Question question = Question.builder()
            .college(college)
            .subject(subject)
            .textBlock(textBlock)
            .examPeriod(request.getExamPeriod())
            .questionText(request.getQuestionText())
            .build();

        List<Choice> choices = request.getChoices().stream()
            .map(c -> Choice.builder()
                .question(question)
                .choiceText(c.getChoiceText())
                .isCorrect(c.getIsCorrect())
                .position(c.getPosition())
                .build())
            .toList();

        question.setChoices(choices);

        Question saved = questionRepository.save(question);

        List<ChoiceResponse> choiceResponses = saved.getChoices().stream()
            .map(c -> new ChoiceResponse(c.getId(), c.getChoiceText(), c.getPosition()))
            .toList();

        return new QuestionResponse(
            saved.getId(),
            saved.getCollege().getName(),
            saved.getSubject().getName(),
            saved.getExamPeriod(),
            saved.getQuestionText(),
            saved.getTextBlock() != null ? saved.getTextBlock().getContent() : null,
            choiceResponses
        );
    }
}