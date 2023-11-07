package engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewQuizDto {

    private String title;
    private String text;
    private List<String> options;
    private Integer answer;
}
