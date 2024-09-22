package com.cogno.quizapp.service.serviceInterfaces;

import com.cogno.quizapp.model.Result;
import com.cogno.quizapp.responses.QuizQuestionListResponse;
import com.cogno.quizapp.responses.QuizResponse;
import com.cogno.quizapp.responses.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    QuizResponse createQuiz(String category, int numQ, String title);

    QuizResponse deleteQuiz(Integer id);

    QuizQuestionListResponse getQuizQuestions(Integer id);

    ResultResponse calculateResult(Integer id, List<Result> respons);
}
