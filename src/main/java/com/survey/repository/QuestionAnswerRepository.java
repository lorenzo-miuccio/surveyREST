package com.survey.repository;

import com.survey.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

    QuestionAnswer findByIdQuestionAndIdAnswer(Long idQuestion, Long idAnswer);

}