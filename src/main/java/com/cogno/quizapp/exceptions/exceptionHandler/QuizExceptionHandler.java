package com.cogno.quizapp.exceptions.exceptionHandler;

import com.cogno.quizapp.enums.errorCodeEnums.QuizErrorCodeEnum;
import com.cogno.quizapp.exceptions.quizExceptions.DuplicateQuizException;
import com.cogno.quizapp.exceptions.quizExceptions.InvalidQuizDataException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizDeleteFailedException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizNotFoundException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizSaveFailedException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizUpdateFailedException;
import com.cogno.quizapp.responses.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Log4j2
@RestControllerAdvice
public class QuizExceptionHandler {
    // Handle Quiz Not Found Exception
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<BaseResponse> handleQuizNotFoundException(QuizNotFoundException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false, QuizErrorCodeEnum.QUIZ_NOT_FOUND.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle Invalid Quiz Data Exception
    @ExceptionHandler(InvalidQuizDataException.class)
    public ResponseEntity<BaseResponse> handleInvalidQuizDataException(InvalidQuizDataException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuizErrorCodeEnum.INVALID_QUIZ_DATA.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle Duplicate Quiz Exception
    @ExceptionHandler(DuplicateQuizException.class)
    public ResponseEntity<BaseResponse> handleDuplicateQuizException(DuplicateQuizException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuizErrorCodeEnum.DUPLICATE_QUIZ.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle Quiz Save Failed Exception
    @ExceptionHandler(QuizSaveFailedException.class)
    public ResponseEntity<BaseResponse> handleQuizSaveFailedException(QuizSaveFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuizErrorCodeEnum.QUIZ_SAVE_FAILED.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Quiz Update Failed Exception
    @ExceptionHandler(QuizUpdateFailedException.class)
    public ResponseEntity<BaseResponse> handleQuizUpdateFailedException(QuizUpdateFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false, QuizErrorCodeEnum.QUIZ_UPDATE_FAILED.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Quiz Delete Failed Exception
    @ExceptionHandler(QuizDeleteFailedException.class)
    public ResponseEntity<BaseResponse> handleQuizDeleteFailedException(QuizDeleteFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(), false, QuizErrorCodeEnum.QUIZ_DELETE_FAILED.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse( "An unexpected error occurred",false,HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
