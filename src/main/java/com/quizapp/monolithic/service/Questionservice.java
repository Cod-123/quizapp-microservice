package com.quizapp.monolithic.service;

import com.quizapp.monolithic.model.Questions;
import com.quizapp.monolithic.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Questionservice {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try
        {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
       // return questionDao.findByCategory(category);
        try
        {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> addQuestion(Questions question) {
         questionDao.save(question);
         return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
}
