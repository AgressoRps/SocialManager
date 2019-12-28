package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.Product;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountWrapper {

    private Long id;

    private ProductWrapper product;

    private String login;

    private String password;

    private String fullName;

    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public AccountWrapper(Account account) {
        toWrapper(account);
    }

    private void toWrapper(Account account) {
        if (account != null) {
            id = account.getId();
            product = new ProductWrapper(account.getProduct());
            login = account.getLogin();
            password = account.getPassword();
            fullName = account.getFullName();
            userName = account.getUserName();
            dateCreate = product.getDateCreate();
        }
    }

    public void fromWrapper(Account account) {
        if (account != null) {
            account.setLogin(login);
            account.setPassword(password);
            account.setFullName(fullName);
            account.setUserName(userName);
        }
    }

}
