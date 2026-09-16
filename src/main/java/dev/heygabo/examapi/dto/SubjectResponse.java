package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class SubjectResponse {
    private Long id;
    private String name;
    private String categoryName;
}