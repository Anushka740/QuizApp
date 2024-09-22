package com.cogno.quizapp.enums;

import lombok.Getter;

@Getter
public enum QuestionEnum {
    PRINT_QUESTIONS("All questions are ptinted successfully"),
    DELETE_QUESTION("Question is deleted successfully"),
    UPDATE_QUESTION("Question is updated successfully"),
    ADD_QUESTION("Question is added successfully");

    private String message;
    QuestionEnum(String message){
        this.message=message;
    }
}
