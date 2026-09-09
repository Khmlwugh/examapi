package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.TextBlockImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TextBlockImageRepository extends JpaRepository<TextBlockImage, Long> {
    List<TextBlockImage> findByTextBlockIdOrderByPosition(Long textBlockId);
}