package com.cogno.quizapp.exceptions.quizExceptions;

public class QuizSaveFailedException extends RuntimeException {
    public QuizSaveFailedException(String message) {
        super(message);
    }
}