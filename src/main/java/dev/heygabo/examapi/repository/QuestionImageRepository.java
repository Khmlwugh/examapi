package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {
    List<QuestionImage> findByQuestionIdOrderByPosition(Long questionId);
}