package com.cogno.quizapp.enums.errorCodeEnums;

import lombok.Getter;

@Getter
public enum QuestionErrorCodeEnum {

    QUESTION_NOT_FOUND(404, "Question not found"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    INVALID_QUESTION_DATA(400, "Invalid question data provided"),
    DUPLICATE_QUESTION(409, "Duplicate question found"),
    QUESTION_SAVE_FAILED(500, "Failed to save the question"),
    QUESTION_UPDATE_FAILED(500, "Failed to update the question"),
    QUESTION_DELETE_FAILED(500, "Failed to delete the question");

    private final Integer code;
    private final String message;

    QuestionErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
