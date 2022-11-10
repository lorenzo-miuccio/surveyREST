package com.survey.repository;

import com.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s where s.id not in ?1")
    List<Survey> findByIdNotIn(Collection<Long> ids);
}
