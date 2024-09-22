package com.cogno.quizapp.exceptions.quizExceptions;

public class QuizUpdateFailedException extends RuntimeException {
    public QuizUpdateFailedException(String message) {
        super(message);
    }
}