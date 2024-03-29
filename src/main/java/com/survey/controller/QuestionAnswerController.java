package com.survey.controller;

import com.survey.model.*;
import com.survey.repository.*;
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
    SubmittedSurveyRepository submittedSurveyRepository;

    @Autowired
    SubmittedAnswerRepository submittedAnswerRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionAnswerRepository questionAnswerRepository;

    @PutMapping("/sendSubmittedSurvey")
    public ResponseEntity<HttpStatus> sendSubmittedSurvey(@RequestParam() String mail,
                                                          @RequestParam() long id_survey,
                                                          @RequestBody List<QuestionAnswer> questionAnswers) {
        try {
            Optional<Survey> s = surveyRepository.findById(id_survey);
            Optional<User> u = userRepository.findByMail(mail);

            if (u.isEmpty() || s.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // check se già è stato submitted
            Optional<SubmittedSurvey> ss = submittedSurveyRepository.findByIdSurveyAndIdMail(id_survey, mail);
            if(ss.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            SubmittedSurvey subSurvey = this.submittedSurveyRepository.save(new SubmittedSurvey(id_survey, mail)); // salvo il submitted survey in tabella

            List<SubmittedAnswer> submittedAnswers = new ArrayList<>();

            for (QuestionAnswer qa : questionAnswers) {
                System.out.println(qa.getIdQuestion() + "    "  + qa.getIdAnswer());
            }


            for (QuestionAnswer qa : questionAnswers) {
                Long idQuestionAnswer = this.questionAnswerRepository.findByIdQuestionAndIdAnswer(qa.getIdQuestion(), qa.getIdAnswer()).getId();
                submittedAnswers.add(new SubmittedAnswer(subSurvey.getId(), idQuestionAnswer));
            }

            if(submittedAnswers.size() != questionAnswers.size()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            List<SubmittedAnswer> submittedAnswerList = this.submittedAnswerRepository.saveAll(submittedAnswers);

            if(submittedAnswerList.size() != submittedAnswers.size()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
