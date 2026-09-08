package dev.heygabo.examapi.service;

import dev.heygabo.examapi.entity.Question;
import dev.heygabo.examapi.repository.QuestionRepository;
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
}