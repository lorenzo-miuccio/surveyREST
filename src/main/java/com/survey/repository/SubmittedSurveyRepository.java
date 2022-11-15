package com.survey.repository;

import com.survey.model.SubmittedSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedSurveyRepository extends JpaRepository<SubmittedSurvey, Long> {

    SubmittedSurvey findByIdSurveyAndIdMail(Long idSurvey, String idMail);
}