package com.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "submitted_answer")
public class SubmittedAnswer {

    @Column(name ="id_submitted_survey")
    private Long id_survey;

    @Column(name ="id_question_answer")
    private Long id_question_answer;

    public SubmittedAnswer() {

    }
    public void setId_survey(Long id_survey) {
        this.id_survey = id_survey;
    }

    public void setId_question_answer(Long id_question_answer) {
        this.id_question_answer = id_question_answer;
    }

    public Long getId_survey() {
        return id_survey;
    }

    public Long getId_question_answer() {
        return id_question_answer;
    }
}