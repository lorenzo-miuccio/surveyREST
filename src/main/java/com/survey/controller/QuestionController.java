package com.survey.controller;

import com.survey.model.Question;
import com.survey.model.Survey;
import com.survey.model.User;
import com.survey.repository.QuestionRepository;
import com.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("survey/api")

public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = new ArrayList<>();

            questionRepository.findAll().forEach(questions::add);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuestionsSurvey/{idSurvey}")
    public ResponseEntity<QuestionsToSend> getAllQuestions(@PathVariable("idSurvey") long idSurvey,
                                                          @RequestParam(defaultValue = "0") int page, // numero pagina
                                                          @RequestParam(defaultValue = "5") int size) { // numero users in una pagina) {
        try {

            Optional<Survey> data = surveyRepository.findById(idSurvey);

            if (data.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Pageable pageCurrent = PageRequest.of(page, size);
            Page<Question> pageRecords = questionRepository.getQuestionsOfSurvey(idSurvey, pageCurrent);
            List<Question> questions = pageRecords.getContent();

            int numbOfQuestions = questionRepository.countQuestionsOfSurvey(idSurvey);

            return new ResponseEntity<>(new QuestionsToSend(questions, numbOfQuestions), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

class QuestionsToSend {
    private int numbOfQuestions;
    private List<Question> questions;

    public QuestionsToSend( List<Question> questions, int numbOfQuestions) {
        this.numbOfQuestions = numbOfQuestions;
        this.questions = questions;
    }

    public int getNumbOfQuestions() {
        return numbOfQuestions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

