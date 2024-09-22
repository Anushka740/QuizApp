package com.cogno.quizapp.responses;

import com.cogno.quizapp.model.QuestionWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionListResponse {
    private String message;
    private Boolean success;
    private List<QuestionWrapper> questionWrapper;
}
