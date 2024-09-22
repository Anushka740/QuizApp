package com.cogno.quizapp.converter;

import com.cogno.quizapp.dto.QuestionDto;
import com.cogno.quizapp.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public QuestionDto entityToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    // Convert DTO to Entity
    public Question dtoToEntity(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }
}
