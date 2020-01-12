package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Accounts;

@Data
@NoArgsConstructor
public class AccountsWrapper {

    private Long id;

    private Long warning;

    private Long success;

    private Long danger;

    private Long primary;

    public AccountsWrapper(Accounts accounts) {
        toWrapper(accounts);
    }

    private void toWrapper(Accounts accounts) {
        if (accounts != null) {
            id = accounts.getId();
            warning = accounts.getWarning();
            success = accounts.getSuccess();
            danger = accounts.getDanger();
            primary = accounts.getPrimary();
        }
    }

    public void fromWrapper(Accounts accounts) {
        if (accounts != null) {
            accounts.setWarning(warning);
            accounts.setSuccess(success);
            accounts.setDanger(danger);
            accounts.setPrimary(primary);
        }
    }

}
