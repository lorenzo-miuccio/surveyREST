package com.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_question")
    private Long id_question;

    @Column(name = "id_answer")
    private Long id_answer;

    public QuestionAnswer(Long id, Long id_question, Long id_answer) {
        this.id = id;
        this.id_question = id_question;
        this.id_answer = id_answer;
    }

    public QuestionAnswer() {
    }

    public void setId_question(Long id_question) {
        this.id_question = id_question;
    }

    public void setId_answer(Long id_answer) {
        this.id_answer = id_answer;
    }

    public Long getId_question() {
        return id_question;
    }

    public Long getId_answer() {
        return id_answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}