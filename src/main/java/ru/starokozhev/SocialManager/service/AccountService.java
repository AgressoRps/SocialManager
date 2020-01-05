package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.repository.AccountRepository;
import ru.starokozhev.SocialManager.repository.ProxyRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ProxyRepository proxyRepository;

    @Transactional
    public AccountWrapper add(AccountWrapper wrapper) {
        Account account = new Account();

        Account byLogin = accountRepository.findAccountByLogin(wrapper.getLogin());

        if (byLogin != null)
            throw new IllegalArgumentException("Аккаунт с таким логином уже существует!");

        if (wrapper.getProxy() != null) {
            Proxy proxy = proxyRepository.findProxyById(wrapper.getProxy());

            if (proxy == null)
                throw new IllegalArgumentException("Указанный прокси не найден!");

            account.setProxy(proxy);
        }

        wrapper.fromWrapper(account);

        account.setDateCreate(LocalDateTime.now());

        return new AccountWrapper(accountRepository.save(account));
    }

    @Transactional
    public AccountWrapper edit(AccountWrapper wrapper) {
        return null;
    }

    public AccountWrapper get(Long id) {
        return null;
    }

    public void delete(Long id) {

    }

    public Page<AccountWrapper> list(AccountFilter filter) {
        //TODO
        return null;
    }

}
