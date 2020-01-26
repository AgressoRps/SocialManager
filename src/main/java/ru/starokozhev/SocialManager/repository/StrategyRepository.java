package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Strategy;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    Strategy findStrategyById(Long id);

    List<Strategy> findAllByBot(Bot bot);

    List<Strategy> findAllByUser(User user);

    Strategy findStrategyByNameAndUser(String name, User user);

}
