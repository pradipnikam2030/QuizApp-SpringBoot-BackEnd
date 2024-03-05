package com.example.quizapp.service;

import com.example.quizapp.entities.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

         ResponseEntity<List<Question>> getAllQuestions();

         ResponseEntity<List<Question>> findByCategory(String category);

         ResponseEntity<String>  addQuestion(Question question);

         ResponseEntity<Question> updateQuestionById(int id, Question question);

         ResponseEntity<String> deleteQuestionById(int id);



}
