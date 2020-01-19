package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.entity.*;
import ru.starokozhev.SocialManager.repository.AccountRepository;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.ProxyRepository;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BotRepository botRepository;
    private final StrategyRepository strategyRepository;
    private final ProxyRepository proxyRepository;
    private final UserService userService;
    private final ProxyService proxyService;

    @Transactional
    public AccountWrapper add(AccountWrapper wrapper) {
        Account account = new Account();

        Account existAccount = accountRepository.findByLogin(wrapper.getLogin());
        if (existAccount != null)
            throw new IllegalArgumentException("Аккаунт с таким логином уже существует!");

        wrapper.fromWrapper(account);

        account.setUser(userService.getCurrentUser());

        if (wrapper.getBot() != null) {
            Bot bot = botRepository.findByName(wrapper.getBot().getName());

            if (bot == null)
                throw new IllegalArgumentException("Указанный бот не найден!");

            account.setBot(bot);
        }

        if (wrapper.getStrategy() != null && wrapper.getStrategy().getName() != null) {
            Strategy strategy = strategyRepository.findStrategyByName(wrapper.getStrategy().getName());

            if (strategy == null)
                throw new IllegalArgumentException("Указанная стратегия не найдена!");

            account.setStrategy(strategy);
        }

        Mail mail = new Mail();
        wrapper.getMail().fromWrapper(mail);
        account.setMail(mail);

        if (wrapper.getProxy() != null && wrapper.getProxy().getIp() != null && wrapper.getProxy().getPort() != null) {
            Proxy proxy = proxyRepository.findProxyByIpAndPort(wrapper.getProxy().getIp(), wrapper.getProxy().getPort());

            if (proxy == null) {
                ProxyWrapper proxyWrapper = proxyService.add(wrapper.getProxy());

                proxy = proxyRepository.findProxyById(proxyWrapper.getId());
            }

            account.setProxy(proxy);
        }

        return new AccountWrapper(accountRepository.save(account));
    }

}
