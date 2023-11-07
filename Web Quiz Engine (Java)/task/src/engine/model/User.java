package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Column(name = "password")
    @Length(min = 5)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Collection<Quiz> quizzes;
}
