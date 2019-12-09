package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.entity.Views;
import ru.starokozhev.SocialManager.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserWrapper {

    private Long id;

    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @NotBlank(message = "Необходимо указать логин")
    private String login;

    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @JsonView(Views.AccessForRegister.class)
    @Max(value = 36, message = "Пароль слишком длинный. Максимальная длина 32 символа.")
    @Min(value = 4, message = "Пароль должен быть больше 4 символов.")
    @NonNull
    private String password;

    private LocalDateTime dateRegister;

    private LocalDateTime dateLastAuth;

    private LocalDateTime dateLastOperation;

    private Role role;

    private Double balance;

    public UserWrapper(User user) {
        toWrapper(user);
    }

    private void toWrapper(User user) {
        if (user != null) {
            id = user.getId();
            name = user.getName();
            login = user.getLogin();
            email = user.getEmail();
            dateRegister = user.getDateRegister();
            dateLastAuth = user.getDateLastAuth();
            dateLastOperation = user.getDateLastOperation();
            role = user.getRole();
            balance = user.getBalance();
        }
    }

    public void fromWrapper(User user) {
        if (user != null) {
            user.setName(name);
        }
    }

}
