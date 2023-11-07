package engine.service;

import engine.dto.AnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

    public AnswerDto checkAnswer(Integer answerIndex) {
        AnswerDto answerDto = new AnswerDto();
        if (answerIndex.equals(2)) {
            answerDto.setSuccess(true);
            answerDto.setFeedback("Congratulations, you're right!");
        }
        return answerDto;
    }
}
