package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Strategy;

import java.util.List;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    Strategy findStrategyById(Long id);

    List<Strategy> findAllByBot(Bot bot);

}
