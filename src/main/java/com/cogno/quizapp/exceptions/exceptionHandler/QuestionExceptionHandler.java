package com.cogno.quizapp.exceptions.exceptionHandler;

import com.cogno.quizapp.enums.errorCodeEnums.QuestionErrorCodeEnum;
import com.cogno.quizapp.exceptions.questionExceptions.DuplicateQuestionException;
import com.cogno.quizapp.exceptions.questionExceptions.InvalidQuestionDataException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionDeleteFailedException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionNotFoundException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionSaveFailedException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionUpdateFailedException;
import com.cogno.quizapp.responses.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Log4j2
@RestControllerAdvice
public class QuestionExceptionHandler {
    // Handle Question Not Found Exception
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<BaseResponse> handleQuestionNotFoundException(QuestionNotFoundException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse( ex.getMessage(),false, QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle Invalid Question Data Exception
    @ExceptionHandler(InvalidQuestionDataException.class)
    public ResponseEntity<BaseResponse> handleInvalidQuestionDataException(InvalidQuestionDataException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle Duplicate Question Exception
    @ExceptionHandler(DuplicateQuestionException.class)
    public ResponseEntity<BaseResponse> handleDuplicateQuestionException(DuplicateQuestionException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuestionErrorCodeEnum.DUPLICATE_QUESTION.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handle Question Save Failed Exception
    @ExceptionHandler(QuestionSaveFailedException.class)
    public ResponseEntity<BaseResponse> handleQuestionSaveFailedException(QuestionSaveFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse( ex.getMessage(),false,QuestionErrorCodeEnum.QUESTION_SAVE_FAILED.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Question Update Failed Exception
    @ExceptionHandler(QuestionUpdateFailedException.class)
    public ResponseEntity<BaseResponse> handleQuestionUpdateFailedException(QuestionUpdateFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuestionErrorCodeEnum.QUESTION_UPDATE_FAILED.getCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Question Delete Failed Exception
    @ExceptionHandler(QuestionDeleteFailedException.class)
    public ResponseEntity<BaseResponse> handleQuestionDeleteFailedException(QuestionDeleteFailedException ex, WebRequest request) {
        log.throwing(ex);
        BaseResponse errorResponse = new BaseResponse(ex.getMessage(),false,QuestionErrorCodeEnum.QUESTION_DELETE_FAILED.getCode());
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
