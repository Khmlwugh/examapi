package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextBlockRepository extends JpaRepository<TextBlock, Long> {
}