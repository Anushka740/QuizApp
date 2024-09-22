package com.cogno.quizapp.exceptions.questionExceptions;

public class QuestionUpdateFailedException extends RuntimeException {
    public QuestionUpdateFailedException(String message) {
        super(message);
    }
}