package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.StrategyWrapper;
import ru.starokozhev.SocialManager.dto.filter.StrategyFilter;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Strategy;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        if (strategy == null)
            throw new IllegalArgumentException("Стратегия не найдена!");

        Bot bot = botRepository.findByNameAndUser(wrapper.getBot().getName(), user);
        if (bot == null)
            throw new IllegalArgumentException("Бот не найден!");

        wrapper.fromWrapper(strategy);
        strategy.setBot(bot);
        strategy.setUser(user);

        return new StrategyWrapper(strategyRepository.save(strategy));
    }

    public StrategyWrapper get(String name) {
        User user = userService.getCurrentUser();
        Strategy strategy = strategyRepository.findStrategyByNameAndUser(name, user);

        if (strategy == null)
            throw new IllegalArgumentException("Стратегия не найдена!");

        return new StrategyWrapper(strategy);
    }

    public void delete(String name) {
        User user = userService.getCurrentUser();
        Strategy strategy = strategyRepository.findStrategyByNameAndUser(name, user);

        if (strategy == null)
            throw new IllegalArgumentException("Стратегия не найдена!");

        strategy.setDateClose(LocalDateTime.now());
        strategyRepository.save(strategy);
    }

    public List<StrategyWrapper> list(StrategyFilter filter) {
        return strategyRepository.findAllByUser(userService.getCurrentUser())
                .stream().map(StrategyWrapper::new).collect(Collectors.toList());
    }

}
