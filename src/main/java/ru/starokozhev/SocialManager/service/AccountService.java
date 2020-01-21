package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.entity.*;
import ru.starokozhev.SocialManager.repository.AccountRepository;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.ProxyRepository;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = userService.getCurrentUser();

        Account existAccount = accountRepository.findByLoginAndUser(wrapper.getLogin(), user);
        if (existAccount != null)
            throw new IllegalArgumentException("Аккаунт с таким логином уже существует!");

        wrapper.fromWrapper(account);

        account.setUser(user);

        if (wrapper.getBot() != null) {
            Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);

            if (bot == null)
                throw new IllegalArgumentException("Указанный бот не найден!");

            account.setBot(bot);
        }

        if (wrapper.getStrategy() != null && wrapper.getStrategy().getName() != null) {
            Strategy strategy = strategyRepository.findStrategyByNameAndUser(wrapper.getStrategy().getName(), user);

            if (strategy == null)
                throw new IllegalArgumentException("Указанная стратегия не найдена!");

            account.setStrategy(strategy);
        }

        Mail mail = new Mail();
        wrapper.getMail().fromWrapper(mail);
        account.setMail(mail);

        if (wrapper.getProxy() != null && wrapper.getProxy().getIp() != null && wrapper.getProxy().getPort() != null) {
            Proxy proxy = proxyRepository.findProxyByIpAndPortAndUser(wrapper.getProxy().getIp(),
                    wrapper.getProxy().getPort(), user);

            if (proxy == null) {
                ProxyWrapper proxyWrapper = proxyService.add(wrapper.getProxy());

                proxy = proxyRepository.findProxyById(proxyWrapper.getId());
            }

            account.setProxy(proxy);
        }

        return new AccountWrapper(accountRepository.save(account));
    }

    @Transactional
    public AccountWrapper edit(AccountWrapper wrapper) {
        User user = userService.getCurrentUser();
        Account account = accountRepository.findAccountById(wrapper.getId());

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        wrapper.fromWrapper(account);

        account.setUser(user);

        if (wrapper.getBot() != null) {
            Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);

            if (bot == null)
                throw new IllegalArgumentException("Указанный бот не найден!");

            account.setBot(bot);
        }

        if (wrapper.getStrategy() != null && wrapper.getStrategy().getName() != null) {
            Strategy strategy = strategyRepository.findStrategyByNameAndUser(wrapper.getStrategy().getName(), user);

            if (strategy == null)
                throw new IllegalArgumentException("Указанная стратегия не найдена!");

            account.setStrategy(strategy);
        }

        Mail mail = new Mail();
        wrapper.getMail().fromWrapper(mail);
        account.setMail(mail);

        if (wrapper.getProxy() != null && wrapper.getProxy().getIp() != null && wrapper.getProxy().getPort() != null) {
            Proxy proxy = proxyRepository.findProxyByIpAndPortAndUser(wrapper.getProxy().getIp(),
                    wrapper.getProxy().getPort(), user);

            if (proxy == null) {
                ProxyWrapper proxyWrapper = proxyService.add(wrapper.getProxy());

                proxy = proxyRepository.findProxyById(proxyWrapper.getId());
            }

            account.setProxy(proxy);
        }

        return new AccountWrapper(accountRepository.save(account));
    }

    public AccountWrapper get(Long id) {
        Account account = accountRepository.findAccountById(id);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        return new AccountWrapper(account);
    }

    public AccountWrapper get(String login) {
        User user = userService.getCurrentUser();
        Account account = accountRepository.findByLoginAndUser(login, user);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        return new AccountWrapper(account);
    }

    public List<AccountWrapper> list(AccountFilter filter) {
        return accountRepository.findAll().stream().map(AccountWrapper::new).collect(Collectors.toList());
    }

    public void delete(String login) {
        User user = userService.getCurrentUser();
        Account account = accountRepository.findByLoginAndUser(login, user);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        account.setDateClose(LocalDateTime.now());

        accountRepository.save(account);
    }

    public void delete(Long id) {
        Account account = accountRepository.findAccountById(id);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        account.setDateClose(LocalDateTime.now());

        accountRepository.save(account);
    }

}
