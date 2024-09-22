package com.cogno.quizapp.enums.errorCodeEnums;

import lombok.Getter;

@Getter
public enum QuizErrorCodeEnum {

    QUIZ_NOT_FOUND(404, "Quiz not found"),
    INVALID_QUIZ_DATA(400, "Invalid quiz data provided"),
    DUPLICATE_QUIZ(409, "Duplicate quiz found"),
    QUIZ_SAVE_FAILED(500, "Failed to save the quiz"),
    QUIZ_UPDATE_FAILED(500, "Failed to update the quiz"),
    QUIZ_DELETE_FAILED(500, "Failed to delete the quiz");

    private final Integer code;
    private final String message;

    QuizErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
