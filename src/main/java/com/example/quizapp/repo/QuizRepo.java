package com.example.quizapp.repo;

import com.example.quizapp.entities.Question;
import com.example.quizapp.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {



}
