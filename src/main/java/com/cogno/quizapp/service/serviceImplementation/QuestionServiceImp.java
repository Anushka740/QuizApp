package com.cogno.quizapp.service.serviceImplementation;

import com.cogno.quizapp.converter.QuestionConverter;
import com.cogno.quizapp.dao.QuestionDao;
import com.cogno.quizapp.dto.QuestionDto;
import com.cogno.quizapp.enums.QuestionEnum;
import com.cogno.quizapp.enums.errorCodeEnums.QuestionErrorCodeEnum;
import com.cogno.quizapp.exceptions.questionExceptions.InvalidQuestionDataException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionDeleteFailedException;
import com.cogno.quizapp.exceptions.questionExceptions.QuestionNotFoundException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizSaveFailedException;
import com.cogno.quizapp.exceptions.quizExceptions.QuizUpdateFailedException;
import com.cogno.quizapp.model.Question;
import com.cogno.quizapp.responses.QuestionListResponse;
import com.cogno.quizapp.responses.QuestionResponse;
import com.cogno.quizapp.service.serviceInterfaces.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionConverter questionConverter;

    @Override
    public QuestionListResponse getAllQuestions() {
        List<Question> questions = questionDao.findAll();
        if (questions.isEmpty()) {
            log.warn(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
            throw new QuestionNotFoundException(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
            //return new QuestionListResponse(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage(),false,new ArrayList<>()); // Return an empty list if no questions are found
        } else {
            log.info(QuestionEnum.PRINT_QUESTIONS.getMessage());
            return new QuestionListResponse(QuestionEnum.PRINT_QUESTIONS.getMessage(),true,questions);  // Return the list of questions
        }
    }

    @Override
    public QuestionListResponse getQuestionsByCategory(String category) {
        List<Question> questions = questionDao.findByCategory(category);
        if (questions.isEmpty()) {
            log.warn(QuestionErrorCodeEnum.CATEGORY_NOT_FOUND.getMessage());
            throw new InvalidQuestionDataException(QuestionErrorCodeEnum.CATEGORY_NOT_FOUND.getMessage());
//            return new QuestionListResponse(QuestionErrorCodeEnum.CATEGORY_NOT_FOUND.getMessage(),false,new ArrayList<>()); // Return an empty list if no questions are found for the category
        } else {
            log.info(QuestionEnum.PRINT_QUESTIONS.getMessage());
            return new QuestionListResponse(QuestionEnum.PRINT_QUESTIONS.getMessage(),true,questions);  // Return the list of questions
        }
    }

    @Override
    public QuestionResponse addQuestion(QuestionDto questionDto) {
        if (questionDto == null || questionDto.getQuestionTitle() == null) {
            log.error(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage());
            throw new InvalidQuestionDataException(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage());
//            return new QuestionResponse(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage(), false, null);
        }

        Question question = this.questionConverter.dtoToEntity(questionDto);
        try {
            questionDao.save(question);  // Save the question to the database
            log.info(QuestionEnum.ADD_QUESTION.getMessage());
            return new QuestionResponse(QuestionEnum.ADD_QUESTION.getMessage(), true, questionDto);
        } catch (Exception e) {
            log.error(QuestionErrorCodeEnum.QUESTION_SAVE_FAILED.getMessage());
            throw new QuizSaveFailedException(QuestionErrorCodeEnum.QUESTION_SAVE_FAILED.getMessage());
            //return new QuestionResponse(QuestionErrorCodeEnum.QUESTION_SAVE_FAILED.getMessage(), false, null);
        }
    }

    @Override
    public QuestionResponse updateQuestion(Integer id, QuestionDto updatedQuestion) {
        if (updatedQuestion == null || id == null) {
            log.error(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage());
            throw new InvalidQuestionDataException(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage());
//            return new QuestionResponse(QuestionErrorCodeEnum.INVALID_QUESTION_DATA.getMessage(), false, null);
        }

        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            existingQuestion.setDifficultylevel(updatedQuestion.getDifficultylevel());
            existingQuestion.setCategory(updatedQuestion.getCategory());

            try {
                questionDao.save(existingQuestion);  // Save the updated question
                log.info(QuestionEnum.UPDATE_QUESTION.getMessage());
                return new QuestionResponse(QuestionEnum.UPDATE_QUESTION.getMessage(), true, updatedQuestion);
            } catch (Exception e) {
                log.error(QuestionErrorCodeEnum.QUESTION_UPDATE_FAILED.getMessage());
                throw new QuizUpdateFailedException(QuestionErrorCodeEnum.QUESTION_UPDATE_FAILED.getMessage());
//                return new QuestionResponse(QuestionErrorCodeEnum.QUESTION_UPDATE_FAILED.getMessage(), false, null);
            }
        } else {
            log.error(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
            throw new QuestionNotFoundException(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
//            return new QuestionResponse(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage(), false, null);
        }
    }

    @Override
    public QuestionResponse deleteQuestion(Integer id) {
        if (id == null || !questionDao.existsById(id)) {
            log.error(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
            throw new QuestionNotFoundException(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage());
//            return new QuestionResponse(QuestionErrorCodeEnum.QUESTION_NOT_FOUND.getMessage(), false, null);
        }

        try {
            questionDao.deleteById(id);  // Delete the question
            log.info(QuestionEnum.DELETE_QUESTION.getMessage());
            return new QuestionResponse(QuestionEnum.DELETE_QUESTION.getMessage(), true, null);
        } catch (Exception e) {
            log.error(QuestionErrorCodeEnum.QUESTION_DELETE_FAILED.getMessage());
            throw new QuestionDeleteFailedException(QuestionErrorCodeEnum.QUESTION_DELETE_FAILED.getMessage());
//            return new QuestionResponse(QuestionErrorCodeEnum.QUESTION_DELETE_FAILED.getMessage(), false, null);
        }
    }
}
