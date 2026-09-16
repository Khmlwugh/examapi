package dev.heygabo.examapi.controller;

import dev.heygabo.examapi.dto.CollegeResponse;
import dev.heygabo.examapi.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
@RequiredArgsConstructor
public class CollegeController {

    private final CollegeService collegeService;

    @GetMapping
    public List<CollegeResponse> getAllColleges() {
        return collegeService.getAllColleges();
    }
} 