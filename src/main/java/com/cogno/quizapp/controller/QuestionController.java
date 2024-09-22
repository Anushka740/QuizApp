package com.cogno.quizapp.controller;

import com.cogno.quizapp.dto.QuestionDto;
import com.cogno.quizapp.model.Question;
import com.cogno.quizapp.responses.QuestionListResponse;
import com.cogno.quizapp.responses.QuestionResponse;
import com.cogno.quizapp.service.serviceInterfaces.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public QuestionListResponse getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public QuestionListResponse getQuestionsByCategory(@PathVariable String category){
        return  questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public QuestionResponse addQuestion(@RequestBody QuestionDto question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public QuestionResponse updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionDto updatedQuestion) {
        return questionService.updateQuestion(id, updatedQuestion);
    }

    @DeleteMapping("delete/{id}")
    public QuestionResponse deleteQuestion(@PathVariable("id") Integer id) {
        return questionService.deleteQuestion(id);
    }



}
