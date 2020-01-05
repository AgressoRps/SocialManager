package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.AccountState;
import ru.starokozhev.SocialManager.enums.AccountType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountWrapper {

    private Long id;

    private Long proxy;

    private String login;

    private String password;

    private String newMail;

    private String passwordFromNewMail;

    private String oldMail;

    private String passwordFromOldMail;

    private AccountState accountState;

    private AccountType accountType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public AccountWrapper(Account account) {
        toWrapper(account);
    }

    private void toWrapper(Account account) {
        if (account != null) {
            id = account.getId();
            proxy = account.getProxy() != null ? account.getProxy().getId() : null;
            login = account.getLogin();
            password = account.getPassword();
            newMail = account.getNewMail();
            passwordFromNewMail = account.getPasswordFromNewMail();
            oldMail = account.getOldMail();
            passwordFromOldMail = account.getPasswordFromOldMail();
            accountState = account.getAccountState();
            accountType = account.getAccountType();
            dateCreate = account.getDateCreate();
        }
    }

    public void fromWrapper(Account account) {
        if (account != null) {
            account.setLogin(login);
            account.setPassword(password);
            account.setNewMail(newMail);
            account.setPasswordFromNewMail(passwordFromNewMail);
            account.setOldMail(oldMail);
            account.setPasswordFromOldMail(passwordFromOldMail);
            account.setAccountState(accountState);
            account.setAccountType(accountType);
        }
    }

}
