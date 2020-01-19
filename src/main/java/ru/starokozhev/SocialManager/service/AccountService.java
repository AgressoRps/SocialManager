package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

}
