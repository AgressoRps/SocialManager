package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    //private final AccountRepository accountRepository;

    @Transactional
    public AccountWrapper add(AccountWrapper wrapper) {
        return null;
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
