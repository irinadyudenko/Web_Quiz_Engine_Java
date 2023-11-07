package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @ElementCollection
    @CollectionTable(
            name="quiz_option",
            joinColumns=@JoinColumn(name="quiz_id")
    )
    private List<String> options;

    @ElementCollection
    @CollectionTable(
            name="quiz_answer",
            joinColumns=@JoinColumn(name="quiz_id")
    )
    private Set<Integer> answer;
}
