package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.BotWrapper;
import ru.starokozhev.SocialManager.dto.EarnedWrapper;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Earned;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.EarnedRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotRepository botRepository;
    private final UserService userService;
    private final EarnedService earnedService;
    private final EarnedRepository earnedRepository;

    public BotWrapper add(BotWrapper wrapper) {
        Bot bot = new Bot();
        User user = userService.getCurrentUser();

        Bot existBot = botRepository.findByNameAndUser(wrapper.getName(), user);

        if (existBot != null)
            throw new IllegalArgumentException("Бот с указанным именем уже существует");

        wrapper.fromWrapper(bot);

        bot.setUser(user);

        if (wrapper.getEarned() != null) {
            EarnedWrapper earnedWrapper = earnedService.add(wrapper.getEarned());

            bot.setEarned(earnedRepository.findEarnedById(earnedWrapper.getId()));
        }

        return new BotWrapper(bot);
    }

    public BotWrapper edit(BotWrapper wrapper) {
        User user = userService.getCurrentUser();
        Bot bot = botRepository.findByNameAndUser(wrapper.getName(), user);

        if (bot == null)
            throw new IllegalArgumentException("Бот не найден");

        wrapper.fromWrapper(bot);

        bot.setUser(user);

        if (wrapper.getEarned() != null) {
            EarnedWrapper earnedWrapper = earnedService.add(wrapper.getEarned());

            bot.setEarned(earnedRepository.findEarnedById(earnedWrapper.getId()));
        }

        return new BotWrapper(bot);
    }

    public BotWrapper get(String name) {
        User user = userService.getCurrentUser();
        Bot bot = botRepository.findByNameAndUser(name, user);

        if (bot == null)
            throw new IllegalArgumentException("Бот не найден");

        return new BotWrapper(bot);
    }

    public void delete(String name) {
        User user = userService.getCurrentUser();
        Bot bot = botRepository.findByNameAndUser(name, user);

        if (bot == null)
            throw new IllegalArgumentException("Бот не найден");

        bot.setDateClose(LocalDateTime.now());
        botRepository.save(bot);
    }

    public List<BotWrapper> list() {
        return botRepository.findAllByUser(userService.getCurrentUser()).stream().map(BotWrapper::new)
                .collect(Collectors.toList());
    }

}
