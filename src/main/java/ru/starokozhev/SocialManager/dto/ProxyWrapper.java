package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProxyWrapper {

    private Long id;

    private String ip;

    private String port;

    private String login;

    private String password;

    private ProxyState proxyState;

    private ProxyHttpType proxyHttpType;

    private List<AccountWrapper> accounts = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateLastUse;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public ProxyWrapper(Proxy proxyProduct) {
        toWrapper(proxyProduct);
    }

    private void toWrapper(Proxy proxyProduct) {
        if (proxyProduct != null) {
            id = proxyProduct.getId();
            ip = proxyProduct.getIp();
            port = proxyProduct.getPort();
            login = proxyProduct.getLogin();
            password = proxyProduct.getPassword();
            proxyState = proxyProduct.getProxyState();
            proxyHttpType = proxyProduct.getProxyHttpType();
            accounts = proxyProduct.getAccounts().stream().map(AccountWrapper::new).collect(Collectors.toList());
            dateLastUse = proxyProduct.getDateLastUse();
            dateCreate = proxyProduct.getDateCreate();
        }
    }

    public void fromWrapper(Proxy proxyProduct) {
        if (proxyProduct != null) {
            proxyProduct.setIp(ip);
            proxyProduct.setPort(port);
            proxyProduct.setLogin(login);
            proxyProduct.setPassword(password);
            proxyProduct.setProxyState(proxyState);
            proxyProduct.setProxyHttpType(proxyHttpType);
        }
    }

}
