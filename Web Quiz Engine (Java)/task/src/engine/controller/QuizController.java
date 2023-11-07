package engine.controller;

import engine.dto.QuizDto;
import engine.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService = new QuizService();

    @GetMapping("/quiz")
    public ResponseEntity<?> getQuiz() {
        return ResponseEntity.ok(new QuizDto());
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> postAnswer(@RequestParam(name = "answer") Integer answerIndex) {
        return new ResponseEntity<>(quizService.checkAnswer(answerIndex), HttpStatus.OK);
    }
}
