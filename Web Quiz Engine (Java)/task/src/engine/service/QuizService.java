package engine.service;

import engine.dto.AnswerDto;
import engine.dto.CheckAnswerDto;
import engine.dto.NewQuizDto;
import engine.dto.QuizDto;
import engine.model.Quiz;
import engine.model.User;
import engine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final UserService userService;

    @Transactional
    public AnswerDto checkAnswer(Integer answerIndex) {
        AnswerDto answerDto = new AnswerDto();
        if (answerIndex.equals(2)) {
            answerDto.setSuccess(true);
            answerDto.setFeedback("Congratulations, you're right!");
        }
        return answerDto;
    }

    @Transactional
    public QuizDto saveNewQuiz(NewQuizDto newQuizDto, String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Quiz quizToSave = modelMapper.map(newQuizDto, Quiz.class);
        quizToSave.setId(quizRepository.count()+1);
        quizToSave.setUser(user);
        Quiz quiz = quizRepository.save(quizToSave);
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

    @Transactional
    public AnswerDto solveTheQuiz(Long id, CheckAnswerDto checkAnswerDto) {
        AnswerDto answerDto = new AnswerDto();
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (quiz.getAnswer().equals(checkAnswerDto.getAnswer())) {
            answerDto.setSuccess(true);
            answerDto.setFeedback("Congratulations, you're right!");
        }
        return answerDto;
    }

    @Transactional
    public void deleteQuiz(Long id, String email) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!quiz.getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        try {
            quizRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
