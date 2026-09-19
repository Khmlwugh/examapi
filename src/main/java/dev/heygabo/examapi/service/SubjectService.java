package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.SubjectResponse;
import dev.heygabo.examapi.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final QuestionRepository questionRepository;

    public List<SubjectResponse> getAllSubjects() {
        return questionRepository.findSubjectsWithQuestions().stream()
            .map(s -> new SubjectResponse(
                s.getId(),
                s.getName(),
                s.getCategory() != null ? s.getCategory().getName() : null
            ))
            .toList();
    }
}