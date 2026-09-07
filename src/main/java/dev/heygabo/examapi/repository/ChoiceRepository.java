package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByQuestionIdOrderByPosition(Long questionId);
}