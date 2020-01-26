package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.StrategyWrapper;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Strategy;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrategyService {

    private final StrategyRepository strategyRepository;
    private final BotRepository botRepository;
    private final UserService userService;

    public StrategyWrapper add(StrategyWrapper wrapper) {
        User user = userService.getCurrentUser();
        Strategy strategy = new Strategy();

        Strategy existStrategy = strategyRepository.findStrategyByNameAndUser(wrapper.getName(), user);
        if (existStrategy != null)
            throw new IllegalArgumentException("Стратегия с таким именем уже существует!");

        Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);
        if (bot == null)
            throw new IllegalArgumentException("Бот не найден!");

        wrapper.fromWrapper(strategy);
        strategy.setBot(bot);
        strategy.setUser(user);

        return new StrategyWrapper(strategyRepository.save(strategy));
    }

    public StrategyWrapper edit(StrategyWrapper wrapper) {
        User user = userService.getCurrentUser();
        Strategy strategy = strategyRepository.findStrategyByNameAndUser(wrapper.getName(), user);
        //TODO TODO TODO


        return null;
    }

    public StrategyWrapper get(String name) {
        return null;
    }

    public void delete(String name) {

    }

    public List<StrategyWrapper> list() {
        return null;
    }

}
