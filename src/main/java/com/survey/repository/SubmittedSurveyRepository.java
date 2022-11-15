package com.survey.repository;

import com.survey.model.SubmittedSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmittedSurveyRepository extends JpaRepository<SubmittedSurvey, Long> {

    Optional<SubmittedSurvey> findByIdSurveyAndIdMail(Long idSurvey, String idMail);
}