package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // For filter-based random retrieval — all matching a given college/subject/period
    List<Question> findByCollegeIdAndSubjectIdAndExamPeriod(
        Long collegeId, Long subjectId, String examPeriod
    );

    // Looser filter — just by subject, if college/period aren't specified
    List<Question> findBySubjectId(Long subjectId);

    // Every question tied to a given college
    List<Question> findByCollegeId(Long collegeId);
}