package ru.starokozhev.SocialManager.dto.vtope;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VtopeAccountsWrapper {

    private Long warning;

    private Long success;

    private Long danger;

    private Long primary;

}
