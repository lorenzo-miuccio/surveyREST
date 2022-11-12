package com.survey.repository;

import com.survey.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, String> {
    Page<Answer> findAll(Pageable pageable);
}
