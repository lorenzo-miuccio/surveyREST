package com.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_question")
    private long id_question;

    @Column(name = "id_answer")
    private long id_answer;


    public void setId_question(long id_question) {
        this.id_question = id_question;
    }

    public void setId_answer(long id_answer) {
        this.id_answer = id_answer;
    }

    public long getId_question() {
        return id_question;
    }

    public long getId_answer() {
        return id_answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}