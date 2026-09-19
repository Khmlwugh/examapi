package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.CollegeResponse;
import dev.heygabo.examapi.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

    private final QuestionRepository questionRepository;

    public List<CollegeResponse> getAllColleges() {
        return questionRepository.findCollegesWithQuestions().stream()
            .map(c -> new CollegeResponse(c.getId(), c.getName()))
            .toList();
    }
}