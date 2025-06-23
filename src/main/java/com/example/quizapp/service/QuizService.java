package com.example.quizapp.service;

import com.example.quizapp.dto.QuizDto;
import com.example.quizapp.entities.Question;
import com.example.quizapp.entities.Quiz;
import com.example.quizapp.entities.Response;
import com.example.quizapp.repo.QuestionRepo;
import com.example.quizapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> listOfQuestions = questionRepo.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz(title, listOfQuestions);

        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuizDto>> getQuizQuestions(int id){
        Optional<Quiz> quizDB = quizRepo.findById(id);

         if (quizDB.isEmpty()){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

         List<QuizDto> questionsList = quizDB.get().getQuestions()
                 .stream()
                 .map(q -> new QuizDto(q.getId(),
                         q.getQuestionTitle(),
                         q.getOption1(),
                         q.getOption2(),
                         q.getOption3(),
                         q.getOption4()))
                 .collect(Collectors.toList());

         return new ResponseEntity<>(questionsList, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
