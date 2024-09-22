package com.cogno.quizapp.controller;

import com.cogno.quizapp.model.Result;
import com.cogno.quizapp.responses.QuizQuestionListResponse;
import com.cogno.quizapp.responses.QuizResponse;
import com.cogno.quizapp.responses.ResultResponse;
import com.cogno.quizapp.service.serviceInterfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public QuizResponse createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public QuizQuestionListResponse getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @DeleteMapping("delete/{id}")
    public QuizResponse deleteQuiz(@PathVariable("id") Integer id){
        return quizService.deleteQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResultResponse submitQuiz(@PathVariable Integer id, @RequestBody List<Result> respons){
        return quizService.calculateResult(id, respons);
    }

}
