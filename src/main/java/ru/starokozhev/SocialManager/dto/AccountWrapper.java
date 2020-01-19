package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.vtope.VtopeAccountService;
import ru.starokozhev.SocialManager.enums.vtope.VtopeProxyMode;

@Data
@NoArgsConstructor
public class AccountWrapper {

    private Long id;

    private BotWrapper bot;

    private VtopeAccountService service;

    private String login;

    private String password;

    private StrategyWrapper strategy;

    private MailWrapper mail;

    private VtopeProxyMode proxyMode;

    private ProxyWrapper proxy;

    public AccountWrapper(Account account) {
        toWrapper(account);
    }

    private void toWrapper(Account account) {
        if (account != null) {
            id = account.getId();
            bot = new BotWrapper(account.getBot());
            service = account.getService();
            login = account.getLogin();
            password = account.getPassword();
            strategy = new StrategyWrapper(account.getStrategy());
            mail = new MailWrapper(account.getMail());
            proxyMode = account.getProxyMode();
            proxy = new ProxyWrapper(account.getProxy());
        }
    }

    public void fromWrapper(Account account) {
        if (account != null) {
            account.setService(service);
            account.setLogin(login);
            account.setPassword(password);
            account.setProxyMode(proxyMode);
        }
    }

}
