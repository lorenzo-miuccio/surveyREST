package com.survey.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "mail", unique = true) // nome colonna Id, Primary key
    private String mail;

    @Column(name = "pass")
    private String pass;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @ManyToMany()
    @JoinTable(name = "submitted_survey",
            joinColumns        = { @JoinColumn(name = "id_mail") },
            inverseJoinColumns = { @JoinColumn(name = "id_survey") })
    private List<Survey> submittedSurveys = new ArrayList<>();

    public User() {

    }


    public User(String mail, String pass, boolean isAdmin) {
        this.mail = mail;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public User(String mail, String pass, Boolean isAdmin, List<Survey> submittedSurveys) {
        this.mail = mail;
        this.pass = pass;
        this.isAdmin = isAdmin;
        this.submittedSurveys = submittedSurveys;
    }

    public List<Survey> getSubmittedSurveys() {
        return submittedSurveys;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setSubmittedSurveys(List<Survey> submittedSurveys) {
        this.submittedSurveys = submittedSurveys;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return "User [mail=" + mail + ", pass=" + pass + ", isAdmin=" + isAdmin + "]";
    }
}
