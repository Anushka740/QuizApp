package com.cogno.quizapp.service.serviceInterfaces;

import com.cogno.quizapp.dao.QuestionDao;
import com.cogno.quizapp.dto.QuestionDto;
import com.cogno.quizapp.model.Question;
import com.cogno.quizapp.responses.QuestionListResponse;
import com.cogno.quizapp.responses.QuestionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    QuestionListResponse getAllQuestions();

    QuestionListResponse getQuestionsByCategory(String category);

    QuestionResponse addQuestion(QuestionDto questionDto);

    QuestionResponse updateQuestion(Integer id, QuestionDto updatedQuestionDto);

    QuestionResponse deleteQuestion(Integer id);
}
