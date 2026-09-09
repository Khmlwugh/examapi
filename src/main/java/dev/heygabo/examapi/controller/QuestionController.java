package dev.heygabo.examapi.controller;

import dev.heygabo.examapi.dto.CreateQuestionRequest;
import dev.heygabo.examapi.dto.QuestionResponse;
import dev.heygabo.examapi.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    
    @GetMapping("/daily/{categoryId}")
    public ResponseEntity<QuestionResponse> getDailyQuestion(@PathVariable Long categoryId) {
        return ResponseEntity.ok(questionService.getDailyQuestionForCategory(categoryId));
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(
            @RequestParam Long collegeId,
            @RequestParam Long subjectId,
            @RequestParam String examPeriod,
            @RequestParam(defaultValue = "4") int count) {
        return ResponseEntity.ok(questionService.getQuizQuestions(collegeId, subjectId, examPeriod, count));
    }
}