package dev.heygabo.examapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateQuestionRequest {

    @NotNull(message = "College is required")
    private Long collegeId;

    @NotNull(message = "Subject is required")
    private Long subjectId;

    private Long textBlockId; // nullable — most questions won't have one

    @NotBlank(message = "Exam period is required")
    private String examPeriod;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotEmpty(message = "At least two choices are required")
    @Size(min = 2, max = 6, message = "Between 2 and 6 choices allowed")
    @Valid // tells Spring to also validate each ChoiceRequest inside this list
    private List<ChoiceRequest> choices;
}