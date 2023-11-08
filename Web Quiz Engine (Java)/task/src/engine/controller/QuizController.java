package engine.controller;

import engine.dto.CheckAnswerDto;
import engine.dto.NewQuizDto;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
    public ResponseEntity<?> getQuizzes(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber) {
        return new ResponseEntity<>(quizService.getQuizzes(pageNumber), HttpStatus.OK);
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> postAnswer(@RequestParam(name = "answer") Integer answerIndex) {
        return new ResponseEntity<>(quizService.checkAnswer(answerIndex), HttpStatus.OK);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<?> addNewQuiz(@Valid @RequestBody NewQuizDto newQuizDto, Principal principal) {
        return new ResponseEntity<>(quizService.saveNewQuiz(newQuizDto, principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<?> solveTheQuiz(@RequestBody CheckAnswerDto checkAnswerDto,
                                          @PathVariable Long id) {
        return new ResponseEntity<>(quizService.solveTheQuiz(id, checkAnswerDto), HttpStatus.OK);
    }
    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id, Principal principal) {
        quizService.deleteQuiz(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

}
