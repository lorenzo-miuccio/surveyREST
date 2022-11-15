package com.survey.model;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(SubmittedAnswerPK.class)
@Entity
@Table(name = "submitted_answer")
public class SubmittedAnswer {
    @Id
    @Column(name = "id_submitted_survey")
    private long id_submitted_survey;

    @Id
    @Column(name = "id_question_answer")
    private long id_question_answer;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_submitted_survey", nullable = false, insertable = false, updatable = false)
    private SubmittedSurvey submitted_survey;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_question_answer", nullable = false, insertable = false, updatable = false)
    private QuestionAnswer question_answer;

    public SubmittedSurvey getSubmitted_survey() {
        return submitted_survey;
    }

    public QuestionAnswer getQuestion_answer() {
        return question_answer;
    }

    public SubmittedAnswer () { }
    public SubmittedAnswer (long id_submitted_survey, long id_question_answer) {
        this.id_submitted_survey = id_submitted_survey;
        this.id_question_answer = id_question_answer;
    }
    public long getId_submitted_survey () { return this.id_submitted_survey; }
    public long getId_question_answer () { return this.id_question_answer; }
    public void setId_submitted_survey (long id_submitted_survey) { this.id_submitted_survey = id_submitted_survey; }
    public void setId_question_answer (long id_question_answer) { this.id_question_answer = id_question_answer; }
}

class SubmittedAnswerPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id_submitted_survey;
    private long id_question_answer;

    public SubmittedAnswerPK () { }
    public SubmittedAnswerPK(long id_submitted_survey, long id_question_answer) {
        this.setId_submitted_survey(id_submitted_survey);
        this.setId_question_answer(id_question_answer);
    }
    public long getId_submitted_survey() {
        return id_submitted_survey;
    }
    public void setId_submitted_survey(long id_submitted_survey) {
        this.id_submitted_survey = id_submitted_survey;
    }
    public long getId_question_answer() {
        return id_question_answer;
    }
    public void setId_question_answer(long id_question_answer) {
        this.id_question_answer = id_question_answer;
    }
}