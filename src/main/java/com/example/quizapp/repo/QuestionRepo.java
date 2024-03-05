package com.example.quizapp.repo;

import com.example.quizapp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);
}
