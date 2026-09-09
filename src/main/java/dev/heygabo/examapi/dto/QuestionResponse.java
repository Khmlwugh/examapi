package dev.heygabo.examapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String collegeName;
    private String subjectName;
    private String examPeriod;
    private String questionText;
    private String textBlockContent;
    private List<String> imageUrls;
    private List<ChoiceResponse> choices;
}