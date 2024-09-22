package com.cogno.quizapp.exceptions.questionExceptions;

public class DuplicateQuestionException extends RuntimeException {
    public DuplicateQuestionException(String message) {
        super(message);
    }
}
