package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.SubjectResponse;
import dev.heygabo.examapi.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll().stream()
            .map(s -> new SubjectResponse(
                s.getId(),
                s.getName(),
                s.getCategory() != null ? s.getCategory().getName() : null
            ))
            .toList();
    }
}