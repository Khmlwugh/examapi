package dev.heygabo.examapi.controller;

import dev.heygabo.examapi.dto.SubjectResponse;
import dev.heygabo.examapi.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectResponse> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}