package com.survey.controller;

import com.survey.model.Survey;
import com.survey.repository.SurveyRepository;
import com.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("survey/api")

public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

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

}

