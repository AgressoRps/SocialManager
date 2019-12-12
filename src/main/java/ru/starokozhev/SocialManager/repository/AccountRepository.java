package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.enums.AccountName;
import ru.starokozhev.SocialManager.enums.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long>,
        JpaSpecificationExecutor<Account> {

    Account findAccountByName(AccountName name);

    Account findAccountByType(AccountType type);

    Account findAccountById(Long id);

}
