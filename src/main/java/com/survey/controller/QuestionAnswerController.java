package com.survey.controller;

import com.survey.model.QuestionAnswer;
import com.survey.model.SubmittedSurvey;
import com.survey.model.Survey;
import com.survey.model.User;
import com.survey.repository.QuestionAnswerRepository;
import com.survey.repository.SubmittedSurveyRepository;
import com.survey.repository.SurveyRepository;
import com.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("survey/api")

public class QuestionAnswerController {

    @Autowired
    QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    SubmittedSurveyRepository submittedSurveyRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/sendSubmittedSurvey")
    public ResponseEntity<HttpStatus> sendSubmittedSurvey(@RequestParam() String mail,
                                                          @RequestParam() long id_survey,
                                                          @RequestBody List<QuestionAnswer> questionAnswers) {
        try {

            Optional<Survey> s = surveyRepository.findById(id_survey);
            Optional<User> u = userRepository.findByMail(mail);

            if(u.isEmpty() || s.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            SubmittedSurvey subSurvey = this.submittedSurveyRepository.save(new SubmittedSurvey(id_survey, mail));

            List<QuestionAnswer> questionAnswerList=  new ArrayList<>();
            for (QuestionAnswer qa : questionAnswers) {
                questionAnswerList.add(this.questionAnswerRepository.findByIdQuestionAndIdAnswer(qa.getId_question(), qa.getId_answer()));
            }


        }
    }


}
