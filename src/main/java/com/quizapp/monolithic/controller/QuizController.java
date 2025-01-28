package com.quizapp.monolithic.controller;

import com.quizapp.monolithic.model.QuestionWrapper;
import com.quizapp.monolithic.model.Questions;
import com.quizapp.monolithic.model.Response;
import com.quizapp.monolithic.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizservice;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        return quizservice.createQuiz(category,numQ, title);
    }

    //to fetch the list of questions for a quiz thru thr quiz id param
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
         return quizservice.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        return quizservice.calculateResult(id, responses);
    }





}
