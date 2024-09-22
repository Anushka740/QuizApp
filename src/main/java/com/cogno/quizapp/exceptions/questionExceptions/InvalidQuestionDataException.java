package com.cogno.quizapp.exceptions.questionExceptions;

public class InvalidQuestionDataException extends RuntimeException {
    public InvalidQuestionDataException(String message) {
        super(message);
    }
}