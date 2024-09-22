package com.cogno.quizapp.dto;

import com.cogno.quizapp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuizDto {
    @NonNull
    private Integer id;
    @NonNull
    private String title;
    private List<Question> questions;
}
