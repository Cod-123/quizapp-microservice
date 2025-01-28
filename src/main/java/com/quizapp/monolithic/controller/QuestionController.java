package com.quizapp.monolithic.controller;


import com.quizapp.monolithic.model.Questions;
import com.quizapp.monolithic.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    Questionservice questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question)
    {
        return questionService.addQuestion(question);
    }


}
