package engine.repository;

import engine.dto.CompletedQuizInfoDto;
import engine.model.CompletedQuiz;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Long>,
        PagingAndSortingRepository<CompletedQuiz, Long> {
    @Query("SELECT new engine.dto.CompletedQuizInfoDto(q.quiz.id, q.completedAt) FROM CompletedQuiz q WHERE q.user = :user")
    Page<CompletedQuizInfoDto> findByUser(@Param("user") User user, Pageable pageable);
}

