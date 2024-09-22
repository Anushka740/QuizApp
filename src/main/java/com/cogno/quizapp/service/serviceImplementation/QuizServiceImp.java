package com.cogno.quizapp.service.serviceImplementation;

import com.cogno.quizapp.converter.QuizConverter;
import com.cogno.quizapp.dao.QuestionDao;
import com.cogno.quizapp.dao.QuizDao;
import com.cogno.quizapp.dto.QuizDto;
import com.cogno.quizapp.enums.QuizEnum;
import com.cogno.quizapp.enums.errorCodeEnums.QuizErrorCodeEnum;
import com.cogno.quizapp.model.Question;
import com.cogno.quizapp.model.QuestionWrapper;
import com.cogno.quizapp.model.Quiz;
import com.cogno.quizapp.model.Result;
import com.cogno.quizapp.responses.QuizQuestionListResponse;
import com.cogno.quizapp.responses.QuizResponse;
import com.cogno.quizapp.responses.ResultResponse;
import com.cogno.quizapp.service.serviceInterfaces.QuizService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class QuizServiceImp implements QuizService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizConverter quizConverter;

    @Override
    public QuizResponse createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        if (!questions.isEmpty()) {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            log.info(QuizEnum.CREATE_QUIZ.getMessage());
            QuizDto quizDto = quizConverter.entityToDto(quiz);
            return new QuizResponse(QuizEnum.CREATE_QUIZ.getMessage(), true, quizDto);
        } else {
            log.error(QuizErrorCodeEnum.INVALID_QUIZ_DATA.getMessage());
            return new QuizResponse(QuizErrorCodeEnum.INVALID_QUIZ_DATA.getMessage(), false, null);
        }
    }

    @Override
    public QuizResponse deleteQuiz(Integer id) {
        if (quizDao.existsById(id)) {
            quizDao.deleteById(id);
            log.info(QuizEnum.DELETE_QUIZ.getMessage());
            return new QuizResponse(QuizEnum.DELETE_QUIZ.getMessage(), true, null);
        } else {
            log.warn(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage());
            return new QuizResponse(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage(), false, null);
        }
    }

    @Override
    public QuizQuestionListResponse getQuizQuestions(Integer id) {
        Optional<Quiz> quizOpt = quizDao.findById(id);
        if (quizOpt.isPresent()) {
            List<Question> questionsFromDB = quizOpt.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }
            log.info(QuizEnum.PRINT_QUIZ.getMessage());
            return new QuizQuestionListResponse(QuizEnum.PRINT_QUIZ.getMessage(), true, questionsForUser);
        } else {
            log.warn(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage());
            return new QuizQuestionListResponse(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage(), false, new ArrayList<>());
        }
    }

    @Override
    public ResultResponse calculateResult(Integer id, List<Result> responses) {
        Optional<Quiz> quizOpt = quizDao.findById(id);
        if (quizOpt.isPresent()) {
            Quiz quiz = quizOpt.get();
            List<Question> questions = quiz.getQuestions();
            int correctAnswers = 0;
            for (int i = 0; i < responses.size(); i++) {
                if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
                    correctAnswers++;
                }
            }
            log.info(QuizEnum.CALCULATE_RESULT.getMessage());
            return new ResultResponse(QuizEnum.CALCULATE_RESULT.getMessage(), true, correctAnswers);
        } else {
            log.warn(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage());
            return new ResultResponse(QuizErrorCodeEnum.QUIZ_NOT_FOUND.getMessage(), false, 0);
        }
    }
}
