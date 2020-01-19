package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface BotRepository extends JpaRepository<Bot, Long> {

    List<Bot> findAllByUser(User user);

    Bot findByName(String name);

    Bot findBotById(Long id);

}
