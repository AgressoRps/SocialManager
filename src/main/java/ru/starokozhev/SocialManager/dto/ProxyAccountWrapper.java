package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.ProxyAccount;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProxyAccountWrapper {

    private Long id;

    private String url;

    private String login;

    private String password;

    private OrderAccountWrapper orderAccount;

    private ProxyState proxyState;

    private ProxyHttpType proxyHttpType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateLastUse;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public ProxyAccountWrapper(ProxyAccount proxyAccount) {
        toWrapper(proxyAccount);
    }

    private void toWrapper(ProxyAccount proxyAccount) {
        if (proxyAccount != null) {
            id = proxyAccount.getId();
            url = proxyAccount.getUrl();
            login = proxyAccount.getLogin();
            password = proxyAccount.getPassword();
            orderAccount = new OrderAccountWrapper(proxyAccount.getOrderAccount());
            proxyState = proxyAccount.getProxyState();
            proxyHttpType = proxyAccount.getProxyHttpType();
            dateLastUse = proxyAccount.getDateLastUse();
            dateCreate = proxyAccount.getDateCreate();
        }
    }

    public void fromWrapper(ProxyAccount proxyAccount) {
        if (proxyAccount != null) {
            proxyAccount.setUrl(url);
            proxyAccount.setLogin(login);
            proxyAccount.setPassword(password);
            proxyAccount.setProxyState(proxyState);
            proxyAccount.setProxyHttpType(proxyHttpType);
        }
    }

}
