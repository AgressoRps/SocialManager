package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.ProxyProduct;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProxyProductWrapper {

    private Long id;

    private String url;

    private String login;

    private String password;

    private OrderProductWrapper orderProduct;

    private ProxyState proxyState;

    private ProxyHttpType proxyHttpType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateLastUse;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public ProxyProductWrapper(ProxyProduct proxyProduct) {
        toWrapper(proxyProduct);
    }

    private void toWrapper(ProxyProduct proxyProduct) {
        if (proxyProduct != null) {
            id = proxyProduct.getId();
            url = proxyProduct.getUrl();
            login = proxyProduct.getLogin();
            password = proxyProduct.getPassword();
            orderProduct = new OrderProductWrapper(proxyProduct.getOrderProduct());
            proxyState = proxyProduct.getProxyState();
            proxyHttpType = proxyProduct.getProxyHttpType();
            dateLastUse = proxyProduct.getDateLastUse();
            dateCreate = proxyProduct.getDateCreate();
        }
    }

    public void fromWrapper(ProxyProduct proxyProduct) {
        if (proxyProduct != null) {
            proxyProduct.setUrl(url);
            proxyProduct.setLogin(login);
            proxyProduct.setPassword(password);
            proxyProduct.setProxyState(proxyState);
            proxyProduct.setProxyHttpType(proxyHttpType);
        }
    }

}
