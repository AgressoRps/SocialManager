package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.AccountState;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountWrapper {

    private Long id;

    private String proxy;

    private String login;

    private String password;

    private String mail;

    private String passwordMail;

    //private String newMail;

    //private String passwordFromNewMail;

    //private String oldMail;

    //private String passwordFromOldMail;

    private AccountState accountState;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateBlocked;

    public AccountWrapper(Account account) {
        toWrapper(account);
    }

    private void toWrapper(Account account) {
        if (account != null) {
            id = account.getId();
            proxy = account.getProxy();
            login = account.getLogin();
            password = account.getPassword();
            mail = account.getMail();
            passwordMail = account.getPasswordMail();
            //newMail = account.getNewMail();
            //passwordFromNewMail = account.getPasswordFromNewMail();
            //oldMail = account.getOldMail();
            //passwordFromOldMail = account.getPasswordFromOldMail();
            accountState = account.getAccountState();
            dateCreate = account.getDateCreate();
        }
    }

    public void fromWrapper(Account account) {
        if (account != null) {
            account.setLogin(login);
            account.setPassword(password);
            account.setMail(mail);
            account.setPasswordMail(passwordMail);
            //account.setNewMail(newMail);
            //account.setPasswordFromNewMail(passwordFromNewMail);
            //account.setOldMail(oldMail);
            //account.setPasswordFromOldMail(passwordFromOldMail);
            account.setAccountState(accountState);
            account.setProxy(proxy);
        }
    }

}
