package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.CollegeResponse;
import dev.heygabo.examapi.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {

    private final CollegeRepository collegeRepository;

    public List<CollegeResponse> getAllColleges() {
        return collegeRepository.findAll().stream()
            .map(c -> new CollegeResponse(c.getId(), c.getName()))
            .toList();
    }
}