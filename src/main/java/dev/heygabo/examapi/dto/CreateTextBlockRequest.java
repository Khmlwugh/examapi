package dev.heygabo.examapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class CreateTextBlockRequest {

    @NotBlank(message = "Content is required")
    private String content;

    private Long subjectId;

    private List<String> imageUrls;
}