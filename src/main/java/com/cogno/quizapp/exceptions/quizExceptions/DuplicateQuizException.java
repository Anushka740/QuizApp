package com.cogno.quizapp.exceptions.quizExceptions;

public class DuplicateQuizException extends RuntimeException {
    public DuplicateQuizException(String message) {
        super(message);
    }
}