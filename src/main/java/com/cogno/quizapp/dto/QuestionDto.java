package com.cogno.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    @NonNull
    private Integer id;
    @NonNull
    private String questionTitle;
    @NonNull
    private String option1;
    @NonNull
    private String option2;
    @NonNull
    private String option3;
    @NonNull
    private String option4;
    @NonNull
    private String rightAnswer;
    @NonNull
    private String difficultylevel;
    private String category;

}
