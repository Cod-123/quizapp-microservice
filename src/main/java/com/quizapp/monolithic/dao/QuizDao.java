package com.quizapp.monolithic.dao;

import com.quizapp.monolithic.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
