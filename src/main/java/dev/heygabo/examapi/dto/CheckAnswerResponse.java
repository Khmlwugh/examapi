package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class CheckAnswerResponse {
    private boolean correct;
    private Long correctChoiceId;
}