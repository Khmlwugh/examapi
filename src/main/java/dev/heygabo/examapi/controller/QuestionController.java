package dev.heygabo.examapi.controller;

import dev.heygabo.examapi.dto.CreateQuestionRequest;
import dev.heygabo.examapi.dto.QuestionResponse;
import dev.heygabo.examapi.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody CreateQuestionRequest request) {
        QuestionResponse response = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}