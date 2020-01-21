package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Mail;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUser(User user);

    List<Account> findAllByBot(Bot bot);

    Account findByLoginAndUser(String login, User user);

    Account findByMailAndUser(Mail mail, User user);

    Account findAccountById(Long id);

}
