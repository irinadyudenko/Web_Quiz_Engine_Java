package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "text")
    @NotBlank
    private String text;

    @ElementCollection
    @CollectionTable(
            name="quiz_option",
            joinColumns=@JoinColumn(name="quiz_id")
    )
    private List<String> options;

    @Column(name = "answer")
    @NotEmpty
    private Integer answer;
}
