package com.quizapp.monolithic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@Data
@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public String getTitle() {
        return title;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany
    private List<Questions> questions;



}
