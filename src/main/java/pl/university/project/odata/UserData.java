package pl.university.project.odata;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserData {
    private Long id;

    @NotNull(message = "Nazwa użytkownika nie może być pusta!")
    @NotBlank(message = "Nazwa użytkownika nie może być pusta!")
    @Size(max = 50, message = "Nazwa użytkownika może się składać tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z]+", message = "Nazwa użytkownika może składać się tylko z liter!")
    private String username;

    @NotNull(message = "Hasło nie może być puste!")
    @NotBlank(message = "Hasło nie może być puste!")
    private String password;
    @NotNull(message = "Powtórz hasło!")
    @NotBlank(message = "Powtórz hasło!")
    private String repeatPassword;
    private String role;
    private boolean enabled;
}
