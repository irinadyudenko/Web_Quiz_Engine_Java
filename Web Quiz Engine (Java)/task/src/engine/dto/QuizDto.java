package engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private String title = "The Java Logo";
    private String text = "What is depicted on the Java logo?";
    private List<String> options = List.of("Robot", "Tea leaf", "Cup of coffee", "Bug");

}
