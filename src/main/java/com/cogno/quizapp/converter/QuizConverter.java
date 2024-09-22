package com.cogno.quizapp.converter;

import com.cogno.quizapp.dto.QuizDto;
import com.cogno.quizapp.model.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizConverter {

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public QuizDto entityToDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    // Convert DTO to Entity
    public Quiz dtoToEntity(QuizDto quizDto) {
        return modelMapper.map(quizDto, Quiz.class);
    }
}
