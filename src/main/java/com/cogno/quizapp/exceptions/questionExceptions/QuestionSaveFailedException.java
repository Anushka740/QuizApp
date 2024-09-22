package com.cogno.quizapp.exceptions.questionExceptions;

public class QuestionSaveFailedException extends RuntimeException {
    public QuestionSaveFailedException(String message) {
        super(message);
    }
}