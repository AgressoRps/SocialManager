package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.enums.ProxyState;
import ru.starokozhev.SocialManager.enums.ProxyType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProxyWrapper {

    private Long id;

    private BotWrapper bot;

    private ProxyType type;

    private String ip;

    private String port;

    private String login;

    private String password;

    private ProxyState proxyState;

    private LocalDateTime dateCreate;

    public ProxyWrapper(Proxy proxy) {
        toWrapper(proxy);
    }

    private void toWrapper(Proxy proxy) {
        if (proxy != null) {
            id = proxy.getId();
            bot = new BotWrapper(proxy.getBot());
            type = proxy.getType();
            ip = proxy.getIp();
            port = proxy.getPort();
            login = proxy.getLogin();
            password = proxy.getPassword();
            proxyState = proxy.getProxyState();
            dateCreate = proxy.getDateCreate();
        }
    }

    public void fromWrapper(Proxy proxy) {
        if (proxy != null) {
            proxy.setType(type);
            proxy.setIp(ip);
            proxy.setPort(port);
            proxy.setLogin(login);
            proxy.setPassword(password);
        }
    }

}
