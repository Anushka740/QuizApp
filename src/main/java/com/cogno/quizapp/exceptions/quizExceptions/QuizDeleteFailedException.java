package com.cogno.quizapp.exceptions.quizExceptions;

public class QuizDeleteFailedException extends RuntimeException {
    public QuizDeleteFailedException(String message) {
        super(message);
    }
}
