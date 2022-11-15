package com.survey.repository;

import com.survey.model.SubmittedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer, Long> {
}
