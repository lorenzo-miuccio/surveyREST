package com.survey.repository;

import com.survey.model.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("select s from Survey s where s.ending_date > ?1 and s.category.name like ?2 and s.name like ?3 ")
    //List<Survey> findFilteredActiveSurveys(Date ending_date, String categoryName, String surveyTitle);
    Page<Survey> findFilteredActiveSurveys(Date ending_date, String categoryName, String surveyTitle, Pageable pageable);


    @Query("""
            select s from Survey s
            where s.ending_date >= ?1 and s.category.name like ?2 and s.name like ?3 and s.id not in ?4""")
    //List<Survey> findFilteredActiveSurveysUnsubmitted(Date ending_date, String categoryName, String surveyTitle, Collection<Long> ids);
    Page<Survey> findFilteredActiveSurveysUnsubmitted(Date ending_date, String categoryName, String surveyTitle, Collection<Long> ids, Pageable pageable);

}
