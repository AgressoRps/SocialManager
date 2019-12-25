package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.enums.AccountName;
import ru.starokozhev.SocialManager.enums.AccountType;

@Data
@NoArgsConstructor
public class OrderWrapper {

    private Long count;
    private AccountName type;

}
