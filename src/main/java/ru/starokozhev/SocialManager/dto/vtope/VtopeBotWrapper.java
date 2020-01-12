package ru.starokozhev.SocialManager.dto.vtope;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotAccess;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotStatus;

@Data
@NoArgsConstructor
public class VtopeBotWrapper {

    private Long id;

    private VtopeBotStatus status;

    private String name;

    private VtopeBotAccess access;

    private VtopeEarnedWrapper earned;

    private VtopeAccountsWrapper accounts;

    private VtopeProxiesWrapper proxies;

}
