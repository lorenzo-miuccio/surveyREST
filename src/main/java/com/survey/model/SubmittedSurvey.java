package com.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "submitted_survey")
public class SubmittedSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_survey")
    private long id_survey;

    @Column(name = "id_mail")
    private String id_mail;

    public SubmittedSurvey() {
    }

    public SubmittedSurvey(long id_survey, String id_mail) {
        this.id_survey = id_survey;
        this.id_mail = id_mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId_survey() {
        return id_survey;
    }

    public String getId_mail() {
        return id_mail;
    }

    public void setId_survey(long id_survey) {
        this.id_survey = id_survey;
    }
}