package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.AccountName;
import ru.starokozhev.SocialManager.enums.AccountType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountWrapper {

    private Long id;

    private AccountName name;

    private AccountType type;

    private Double price;

    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yy HH:mm")
    private LocalDateTime dateCreate;

    public AccountWrapper(Account account) {
        toWrapper(account);
    }

    private void toWrapper(Account account) {
        if (account != null) {
            id = account.getId();
            name = account.getName();
            type = account.getType();
            price = account.getPrice();
            url = account.getUrl();
            dateCreate = account.getDateCreate();
        }
    }

    public void fromWrapper(Account account) {
        if (account != null) {
            account.setName(name);
            account.setType(type);
            account.setPrice(price);
            account.setUrl(url);
        }
    }

}
