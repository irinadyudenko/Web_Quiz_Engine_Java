package engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewQuizDto {

    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @Size(min = 2)
    private List<String> options;
    private List<Integer> answer;
}
