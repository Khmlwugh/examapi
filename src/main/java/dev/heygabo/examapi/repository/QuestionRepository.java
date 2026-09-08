package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCollegeIdAndSubjectIdAndExamPeriod(
        Long collegeId, Long subjectId, String examPeriod
    );

    List<Question> findBySubjectId(Long subjectId);

    List<Question> findByCollegeId(Long collegeId);
    
    List<Question> findBySubject_Category_Id(Long categoryId);
    
    List<Question> findBySubject_Category_IdAndCreatedAtBefore(Long categoryId, LocalDateTime cutoff);
}