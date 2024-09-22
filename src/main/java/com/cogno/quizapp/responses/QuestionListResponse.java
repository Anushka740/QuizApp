package com.cogno.quizapp.responses;

import com.cogno.quizapp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListResponse {

        private String message;
        private Boolean success;
        private List<Question> question;

}
