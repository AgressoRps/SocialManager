package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.repository.AccountRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountWrapper add(AccountWrapper wrapper) {
        Account account = new Account();
        wrapper.fromWrapper(account);

        return new AccountWrapper(accountRepository.save(account));
    }

    @Transactional
    public AccountWrapper edit(AccountWrapper wrapper) {
        Account account = accountRepository.findAccountById(wrapper.getId());

        //TODO выводить id аккаунта, который ищем
        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        wrapper.fromWrapper(account);

        return new AccountWrapper(accountRepository.save(account));
    }

    public AccountWrapper get(Long id) {
        Account account = accountRepository.findAccountById(id);

        //TODO выводить id аккаунта, который ищем
        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        return new AccountWrapper(account);
    }

    public List<AccountWrapper> list(AccountFilter filter) {
        //TODO specifications
        return null;
    }

    public void delete(Long id) {
        Account account = accountRepository.findAccountById(id);

        //TODO выводить id аккаунта, который ищем
        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        account.setDateClose(LocalDateTime.now());
        accountRepository.save(account);
    }

}
