package dev.heygabo.examapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChoiceRequest {

    @NotBlank(message = "Choice text is required")
    private String choiceText;

    @NotNull(message = "isCorrect must be specified")
    private Boolean isCorrect;

    @NotNull(message = "Position is required")
    private Short position;
}