package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter @AllArgsConstructor
public class TextBlockResponse {
    private Long id;
    private String content;
    private List<String> imageUrls;
}