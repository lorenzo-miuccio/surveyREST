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
    private Long id_question;

    @Column(name = "id_answer")
    private Long id_answer;

    public QuestionAnswer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_question(Long id_question) {
        this.id_question = id_question;
    }

    public void setId_answer(Long id_answer) {
        this.id_answer = id_answer;
    }

    public Long getId() {
        return id;
    }

    public Long getId_question() {
        return id_question;
    }

    public Long getId_answer() {
        return id_answer;
    }
}