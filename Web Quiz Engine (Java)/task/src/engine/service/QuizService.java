package engine.service;

import engine.dto.AnswerDto;
import engine.dto.NewQuizDto;
import engine.dto.QuizDto;
import engine.model.Quiz;
import engine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;

    public AnswerDto checkAnswer(Integer answerIndex) {
        AnswerDto answerDto = new AnswerDto();
        if (answerIndex.equals(2)) {
            answerDto.setSuccess(true);
            answerDto.setFeedback("Congratulations, you're right!");
        }
        return answerDto;
    }

    public QuizDto saveNewQuiz(NewQuizDto newQuizDto) {
        Quiz quiz = quizRepository.save(modelMapper.map(newQuizDto, Quiz.class));
        return modelMapper.map(quiz, QuizDto.class);
    }

    public QuizDto getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(quiz, QuizDto.class);
    }

    public List<QuizDto> getQuizzes() {
        List<QuizDto> quizDtos = new ArrayList<>();
        quizRepository.findAll()
                .forEach(quiz -> quizDtos.add(modelMapper.map(quiz, QuizDto.class)));
        return quizDtos;
    }
}
