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
    private Long idSurvey;

    @Column(name = "id_mail")
    private String idMail;

    public SubmittedSurvey() {
    }

    public SubmittedSurvey(Long idSurvey, String idMail) {
        this.idSurvey = idSurvey;
        this.idMail = idMail;
    }

    public Long getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(Long idSurvey) {
        this.idSurvey = idSurvey;
    }

    public String getIdMail() {
        return idMail;
    }

    public void setIdMail(String idMail) {
        this.idMail = idMail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}