package com.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "id_answer")
    private Long idAnswer;

    public QuestionAnswer(Long id, Long idQuestion, Long idAnswer) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.idAnswer = idAnswer;
    }

    public QuestionAnswer() {
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}