package com.survey.repository;

import com.survey.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    QuestionAnswer findById_questionAndId_answer(Long id_question, Long id_answer);

}
