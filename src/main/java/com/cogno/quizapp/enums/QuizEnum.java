package com.cogno.quizapp.enums;

import lombok.Getter;

@Getter
public enum QuizEnum {
    PRINT_QUIZ("{Quiz questions are printing successfully"),
    DELETE_QUIZ("Quiz is deleted successfully"),
    UPDATE_QUIZ("Quiz updated successfully"),
    CREATE_QUIZ("Quiz is created successfully"),
    CALCULATE_RESULT("Result is calculated successfully");

    private String message;
    QuizEnum(String message)
    {
        this.message=message;
    }

    }
