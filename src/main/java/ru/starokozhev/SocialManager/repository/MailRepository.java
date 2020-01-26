package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Mail;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Long> {

    Mail findMailById(Long id);

    Mail findByEmail(String email);

    List<Mail> findAllByUser(User user);

}
