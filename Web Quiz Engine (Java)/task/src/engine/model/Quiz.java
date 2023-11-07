package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="quiz_option",
            joinColumns=@JoinColumn(name="quiz_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> options;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="quiz_answer",
            joinColumns=@JoinColumn(name="quiz_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    private User user;

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof Quiz other)) {
            return false;
        }
        return this.title.equals(other.title) &&
                this.text.equals(other.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

