package com.example.quizapp.controller;

import com.example.quizapp.dto.QuizDto;
import com.example.quizapp.entities.Question;
import com.example.quizapp.entities.Quiz;
import com.example.quizapp.entities.Response;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
         return quizService.createQuiz(category, numQ, title);

    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuizDto>> getQuiz(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
