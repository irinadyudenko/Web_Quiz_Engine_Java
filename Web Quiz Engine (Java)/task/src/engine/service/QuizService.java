package engine.service;

import engine.dto.AnswerDto;
import engine.dto.CheckAnswerDto;
import engine.dto.NewQuizDto;
import engine.dto.QuizDto;
import engine.model.CompletedQuiz;
import engine.model.Quiz;
import engine.model.User;
import engine.repository.CompletedQuizRepository;
import engine.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final CompletedQuizRepository completedQuizRepository;
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
        quizToSave.setUser(user);
        Quiz quiz = quizRepository.save(quizToSave);
        return modelMapper.map(quiz, QuizDto.class);
    }

    public QuizDto getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(quiz, QuizDto.class);
    }

    public Page<Quiz> getQuizzes(Integer pageNumber) {
        return quizRepository.findAll(PageRequest.of(pageNumber, 10));

    }

    @Transactional
    public AnswerDto solveTheQuiz(Long id, CheckAnswerDto checkAnswerDto, String userEmail) {
        AnswerDto answerDto = new AnswerDto();
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if (quiz.getAnswer().equals(checkAnswerDto.getAnswer())) {
            answerDto.setSuccess(true);
            answerDto.setFeedback("Congratulations, you're right!");
            CompletedQuiz completedQuiz = new CompletedQuiz();
            completedQuiz.setCompletedAt(LocalDateTime.now());
            completedQuiz.setUser(user);
            completedQuiz.setQuiz(quiz);
            completedQuizRepository.save(completedQuiz);
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

    public Page<?> getCompletedQuizzes(Integer pageNumber, String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return completedQuizRepository.findByUser(user, PageRequest.of(pageNumber, 10,  Sort.by("completedAt").descending()));
    }
}
