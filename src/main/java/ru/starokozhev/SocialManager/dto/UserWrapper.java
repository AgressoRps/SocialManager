package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserWrapper {

    @JsonView(Views.AccessList.class)
    private Long id;

    @NotBlank(message = "Необходимо указать имя")
    @JsonView(Views.AccessList.class)
    private String name;

    @NotBlank(message = "Необходимо указать логин")
    @JsonView(Views.AccessCard.class)
    private String login;

    @Email(message = "Email должен быть корректным адресом электронной почты")
    @JsonView(Views.AccessCard.class)
    private String email;

    @JsonView(Views.AccessForRegister.class)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    @JsonView(Views.AccessCard.class)
    private LocalDateTime dateRegister;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    @JsonView(Views.AccessCard.class)
    private LocalDateTime dateLastAuth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    @JsonView(Views.AccessCard.class)
    private LocalDateTime dateLastOperation;

    @JsonView(Views.AccessCard.class)
    private Role role;

    @JsonView(Views.AccessCard.class)
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
            //password = user.getPassword();
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
