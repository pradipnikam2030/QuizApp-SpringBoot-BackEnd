package com.example.quizapp.controller;

import com.example.quizapp.entities.Question;
import com.example.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String sendString(){
        return "Hello World";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("category{category}")
    public ResponseEntity<List<Question>> findByCategory(@PathVariable String category){
        return questionService.findByCategory(category);
    }

    @PutMapping("/update{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question){
         return questionService.updateQuestionById(id, question);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestionById(id);
    }

}
