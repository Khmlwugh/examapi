package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
}