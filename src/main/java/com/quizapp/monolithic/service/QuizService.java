package com.quizapp.monolithic.service;

import com.quizapp.monolithic.dao.QuestionDao;
import com.quizapp.monolithic.dao.QuizDao;
import com.quizapp.monolithic.model.QuestionWrapper;
import com.quizapp.monolithic.model.Questions;
import com.quizapp.monolithic.model.Quiz;
import com.quizapp.monolithic.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizdao.findById(id);
        List<Questions> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Questions q : questionsfromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);


    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizdao.findById(id).get(); //instaed of optinal data use get()
        List<Questions> questions = quiz.getQuestions();
        int right=0;
        int i=0;

        for(Response response : responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        for(Questions q:questions)
        {
            for(Response response: responses)
            {
                if(q.getId().equals(response.getId()))
                {
                    if(q.getRightAnswer().equals(response.getResponse()))
                    {
                        right++;
                    }
                }
            }
        }


        return new ResponseEntity<>(right, HttpStatus.OK);


    }
}
