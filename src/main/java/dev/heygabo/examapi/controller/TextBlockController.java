package dev.heygabo.examapi.controller;

import dev.heygabo.examapi.dto.CreateTextBlockRequest;
import dev.heygabo.examapi.dto.TextBlockResponse;
import dev.heygabo.examapi.service.TextBlockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/text-blocks")
@RequiredArgsConstructor
public class TextBlockController {

    private final TextBlockService textBlockService;

    @PostMapping
    public ResponseEntity<TextBlockResponse> createTextBlock(@Valid @RequestBody CreateTextBlockRequest request) {
        TextBlockResponse response = textBlockService.createTextBlock(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}