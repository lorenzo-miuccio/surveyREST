package com.survey.repository;

import com.survey.model.SubmittedSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmittedSurveyRepository extends JpaRepository<SubmittedSurvey, Long> {
    List<SubmittedSurvey> findById_surveyAndId_mail(long id_survey, long id_mail);

}
