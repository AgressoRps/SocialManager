package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.BoughtAccount;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface BoughtAccountRepository extends JpaRepository<BoughtAccount, Long>,
        JpaSpecificationExecutor<BoughtAccount> {

    List<BoughtAccount> findAllByUser(User user);

    List<BoughtAccount> findAllByIsPayed(Boolean isPayed);

    BoughtAccount findBoughtAccountById(Long id);

}
