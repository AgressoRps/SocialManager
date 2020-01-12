package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.AccountPreparationStatus;
import ru.starokozhev.SocialManager.repository.AccountRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountWrapper add(AccountWrapper wrapper) {
        Account account = new Account();

        Account byLogin = accountRepository.findAccountByLogin(wrapper.getLogin());

        if (byLogin != null)
            throw new IllegalArgumentException("Аккаунт с таким логином уже существует!");

        wrapper.fromWrapper(account);

        account.setDateCreate(LocalDateTime.now());

        return new AccountWrapper(accountRepository.save(account));
    }

    @Transactional
    public AccountWrapper edit(AccountWrapper wrapper) {
        Account account = accountRepository.findAccountById(wrapper.getId());

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        wrapper.fromWrapper(account);

        if (wrapper.getDateBlocked() != null)
            account.setDateBlocked(wrapper.getDateBlocked());

        return new AccountWrapper(accountRepository.save(account));
    }

    public AccountPreparationStatus preparation(Long id) {


        return AccountPreparationStatus.SUCCESS;
    }

    public AccountWrapper get(Long id) {
        Account account = accountRepository.findAccountById(id);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        return new AccountWrapper(account);
    }

    public void delete(Long id) {
        Account account = accountRepository.findAccountById(id);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        account.setDateClose(LocalDateTime.now());

        accountRepository.save(account);
    }

    public void block(Long id) {
        Account account = accountRepository.findAccountById(id);

        if (account == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        account.setDateBlocked(LocalDateTime.now());

        accountRepository.save(account);
    }

    public List<AccountWrapper> list(AccountFilter filter) {
        return accountRepository.findAll().stream().map(AccountWrapper::new).collect(Collectors.toList());
    }

}
