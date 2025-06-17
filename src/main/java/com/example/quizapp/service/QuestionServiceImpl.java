package com.example.quizapp.service;

import com.example.quizapp.entities.Question;
import com.example.quizapp.repo.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service @Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;




    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
         try {

             return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
         }
         catch (Exception e) {
             log.error("Something Error", e);
         }
         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<Question>> findByCategory(String category) {
        try{
            return  new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);

        }
        catch (Exception e){
            log.error("something went wrong", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
 
    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionRepo.save(question);
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Something Error", e);
        }
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Question> updateQuestionById(int id, Question question) {
         try{
              Optional<Question> question1=questionRepo.findById(id);
              if(question1.isPresent()){
                  Question originalQuestion=question1.get();
                  if(Objects.nonNull(question.getQuestionTitle()) && !"".equalsIgnoreCase(question.getQuestionTitle())){
                      originalQuestion.setQuestionTitle(question.getQuestionTitle());
                  }
                  if(Objects.nonNull(question.getOption1()) && !"".equalsIgnoreCase(question.getOption1())){
                      originalQuestion.setOption1(question.getOption1());
                  }
                  if(Objects.nonNull(question.getOption2()) && !"".equalsIgnoreCase(question.getOption2())){
                      originalQuestion.setOption2(question.getOption2());
                  }
                  if(Objects.nonNull(question.getOption3()) && !"".equalsIgnoreCase(question.getOption3())){
                      originalQuestion.setOption3(question.getOption3());
                  }
                  if(Objects.nonNull(question.getOption4()) && !"".equalsIgnoreCase(question.getOption4())){
                      originalQuestion.setOption4(question.getOption4());
                  }
                  if(Objects.nonNull(question.getRightAnswer()) && !"".equalsIgnoreCase(question.getRightAnswer())){
                      originalQuestion.setRightAnswer(question.getRightAnswer());
                  }
                  if(Objects.nonNull(question.getDifficultyLevel()) && !"".equalsIgnoreCase(question.getDifficultyLevel())){
                      originalQuestion.setDifficultyLevel(question.getDifficultyLevel());
                  }
                  if(Objects.nonNull(question.getCategory()) && !"".equalsIgnoreCase(question.getCategory())){
                      originalQuestion.setCategory(question.getCategory());
                  }
                  return new ResponseEntity<>(originalQuestion, HttpStatus.ACCEPTED);
              }

         }catch (Exception e){
             log.error("Something Error", e);
         }
         return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteQuestionById(int id) {
       try{
           Optional<Question> question = questionRepo.findById(id);
           if (question.isPresent()){
               questionRepo.deleteById(id);
               return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
           }
           return new ResponseEntity<>("Question with this ${id} is not present", HttpStatus.OK);

       }catch (Exception e){
           log.error("Something Error", e);
       }
       return new ResponseEntity<>("Deleted Unsuccessful", HttpStatus.BAD_REQUEST);
    }
}
