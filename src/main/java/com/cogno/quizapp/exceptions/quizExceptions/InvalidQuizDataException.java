package com.cogno.quizapp.exceptions.quizExceptions;

public class InvalidQuizDataException extends RuntimeException {
    public InvalidQuizDataException(String message) {
        super(message);
    }
}