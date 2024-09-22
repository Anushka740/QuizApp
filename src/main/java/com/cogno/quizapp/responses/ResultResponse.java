package com.cogno.quizapp.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultResponse {
    private String message;
    private Boolean success;
    private Integer result;
}
