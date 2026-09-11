package dev.heygabo.examapi.repository;

import dev.heygabo.examapi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE " +
		       "(:collegeId IS NULL OR q.college.id = :collegeId) AND " +
		       "(:subjectId IS NULL OR q.subject.id = :subjectId) AND " +
		       "(:examPeriod IS NULL OR q.examPeriod = :examPeriod)")
		List<Question> findByOptionalFilters(
		    @Param("collegeId") Long collegeId,
		    @Param("subjectId") Long subjectId,
		    @Param("examPeriod") String examPeriod
		);

    List<Question> findBySubjectId(Long subjectId);

    List<Question> findByCollegeId(Long collegeId);
    
    List<Question> findBySubject_Category_Id(Long categoryId);
    
    List<Question> findBySubject_Category_IdAndCreatedAtBefore(Long categoryId, LocalDateTime cutoff);
}