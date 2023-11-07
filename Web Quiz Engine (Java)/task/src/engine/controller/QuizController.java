package engine.controller;

import engine.dto.NewQuizDto;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<?> getQuizzes() {
        return new ResponseEntity<>(quizService.getQuizzes(), HttpStatus.OK);
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> postAnswer(@RequestParam(name = "answer") Integer answerIndex) {
        return new ResponseEntity<>(quizService.checkAnswer(answerIndex), HttpStatus.OK);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<?> addNewQuiz(@RequestBody NewQuizDto newQuizDto) {
        return new ResponseEntity<>(quizService.saveNewQuiz(newQuizDto), HttpStatus.OK);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<?> solveTheQuiz(@RequestParam(name = "answer") Integer answerIndex,
                                          @PathVariable Long id) {
        return new ResponseEntity<>(quizService.solveTheQuiz(id, answerIndex), HttpStatus.OK);
    }
}
