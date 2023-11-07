package engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Pattern(regexp = ".+@.+\\..+")
    @NotEmpty
    private String email;

    @Length(min = 5)
    @NotBlank
    private String password;
}

