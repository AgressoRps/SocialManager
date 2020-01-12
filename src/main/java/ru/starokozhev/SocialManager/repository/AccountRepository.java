package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByLogin(String login);

    Account findAccountById(Long id);

}
