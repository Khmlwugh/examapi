package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ChoiceResponse {
    private Long id;
    private String choiceText;
    private Short position;
}