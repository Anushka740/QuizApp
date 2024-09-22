package com.cogno.quizapp.exceptions.questionExceptions;

public class QuestionDeleteFailedException extends RuntimeException {
    public QuestionDeleteFailedException(String message) {
        super(message);
    }
}