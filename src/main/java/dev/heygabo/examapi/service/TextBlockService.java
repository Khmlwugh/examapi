package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.CreateTextBlockRequest;
import dev.heygabo.examapi.dto.TextBlockResponse;
import dev.heygabo.examapi.entity.Subject;
import dev.heygabo.examapi.entity.TextBlock;
import dev.heygabo.examapi.entity.TextBlockImage;
import dev.heygabo.examapi.repository.SubjectRepository;
import dev.heygabo.examapi.repository.TextBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TextBlockService {

    private final TextBlockRepository textBlockRepository;
    private final SubjectRepository subjectRepository;

    public TextBlockResponse createTextBlock(CreateTextBlockRequest request) {

        Subject subject = null;
        if (request.getSubjectId() != null) {
            subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + request.getSubjectId()));
        }

        TextBlock textBlock = TextBlock.builder()
            .content(request.getContent())
            .subject(subject)
            .build();

        List<TextBlockImage> images = new ArrayList<>();
        if (request.getImageUrls() != null) {
            short position = 1;
            for (String url : request.getImageUrls()) {
                images.add(TextBlockImage.builder()
                    .textBlock(textBlock)
                    .imageUrl(url)
                    .position(position++)
                    .build());
            }
        }
        textBlock.setImages(images);

        TextBlock saved = textBlockRepository.save(textBlock);

        List<String> imageUrls = saved.getImages().stream()
            .map(TextBlockImage::getImageUrl)
            .toList();

        return new TextBlockResponse(saved.getId(), saved.getContent(), imageUrls);
    }
}