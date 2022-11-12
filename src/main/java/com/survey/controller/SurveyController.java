package com.survey.controller;

import com.survey.model.Survey;
import com.survey.model.User;
import com.survey.repository.SurveyRepository;
import com.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("survey/api")

public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/surveys")
    public ResponseEntity<List<Survey>> getAllSurveys()
    {
        List<Survey> surveys = new ArrayList<>();
        try
        {
            surveys.addAll(surveyRepository.findAll());

            if (surveys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // torna uno status HTTP
            }
            return new ResponseEntity<>(surveys, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/submittedSurveys/{mail}")
    public ResponseEntity<List<Survey>> getSubmittedSurveysByUser(@PathVariable("mail") String mail) {

        try {
            Optional<User> user = userRepository.findByMail(mail);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(user.get().getSubmittedSurveys(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/notSubmittedSurveys/{mail}")
    public ResponseEntity<SurveysToSend> getNotSubmittedSurveysByUser(@PathVariable("mail") String mail,
                                                                     @RequestParam(defaultValue = "0")  int page, // numero pagina
                                                                     @RequestParam(defaultValue = "5") int size, // numero users in una pagina
                                                                     @RequestParam(defaultValue = "%") String categoryName,
                                                                     @RequestParam(defaultValue = "%") String surveyTitle,
                                                                      @RequestParam(defaultValue = "ending_date") String sortColumn) {

        try {

            categoryName = categoryName.equals("%") ? categoryName : "%" + categoryName + "%";
            surveyTitle = surveyTitle.equals("%") ? surveyTitle : "%" + surveyTitle + "%";


            Optional<User> data = userRepository.findByMail(mail);

            if (data.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            User user = data.get();

            List<Order> orders = new ArrayList<>();

            //
            Order order1 = new Order(Sort.Direction.ASC, sortColumn); // default is ending_date
            orders.add(order1);

            Order order2 = new Order(Sort.Direction.ASC, "name"); // if same first criterion
            orders.add(order2);

            Pageable pageCurrent   = PageRequest.of(page, size, Sort.by(orders));

            List<Survey> submittedSurveys = user.getSubmittedSurveys();

            if(submittedSurveys.isEmpty()) {

                Page<Survey> pageRecords = surveyRepository.findFilteredActiveSurveys(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), categoryName, surveyTitle, pageCurrent);
                List<Survey> surveys = pageRecords.getContent();
                long num = surveyRepository.countFilteredActiveSurveys(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), categoryName, surveyTitle);
                SurveysToSend surveyToSend = new SurveysToSend(surveys, num);

                return new ResponseEntity<>(surveyToSend, HttpStatus.OK);
            }

            List<Long> ids = new ArrayList<>();
            for(Survey s : submittedSurveys) {
                ids.add(s.getId());
            }

            Page<Survey> pageRecords = surveyRepository.findFilteredActiveSurveysUnsubmitted(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), categoryName, surveyTitle, ids, pageCurrent);
            List<Survey> surveys = pageRecords.getContent();
            long num = surveyRepository.countFilteredActiveSurveysUnsubmitted(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), categoryName, surveyTitle, ids);
            SurveysToSend surveyToSend = new SurveysToSend(surveys, num);
            return new ResponseEntity<>(surveyToSend, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

class SurveysToSend {
    private List<Survey> surveys;
    private long numbOfSurveys;

    public SurveysToSend(List<Survey> surveys, long numbOfSurveys) {
        this.surveys = surveys;
        this.numbOfSurveys = numbOfSurveys;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public long getNumbOfSurveys() {
        return numbOfSurveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void setNumbOfSurveys(long numbOfSurveys) {
        this.numbOfSurveys = numbOfSurveys;
    }
}