package com.survey.repository;

import com.survey.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = """
        SELECT distinct (q.id), q.question, q.id_category
        from question q
        INNER JOIN question_answer qa ON qa.id_question = q.id
        INNER JOIN survey_composition sc ON sc.id_question_answer = qa.id
        WHERE sc.id_survey=?1""", nativeQuery = true,
            countQuery = """
        SELECT  COUNT(distinct (q.id)) from question q
        INNER JOIN question_answer qa ON qa.id_question = q.id
        INNER JOIN survey_composition sc ON sc.id_question_answer = qa.id
        WHERE sc.id_survey=?1
        """)
    Page<Question> getQuestionsOfSurvey(long id_survey, Pageable pageable);

    @Query(value = """
            SELECT COUNT(DISTINCT (q.id)) from question q
            INNER JOIN question_answer qa ON qa.id_question = q.id
            INNER JOIN survey_composition sc ON sc.id_question_answer = qa.id
            WHERE sc.id_survey=?1""", nativeQuery = true)
    int countQuestionsOfSurvey(long id_survey);
}
