package dev.heygabo.examapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckAnswerRequest {

    @NotNull(message = "choiceId is required")
    private Long choiceId;
}