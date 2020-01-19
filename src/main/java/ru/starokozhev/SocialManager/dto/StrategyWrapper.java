package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Strategy;

@Data
@NoArgsConstructor
public class StrategyWrapper {

    private Long id;

    private BotWrapper bot;

    private String name;

    public StrategyWrapper(Strategy strategy) {
        toWrapper(strategy);
    }

    private void toWrapper(Strategy strategy) {
        if (strategy != null) {
            id = strategy.getId();
            bot = new BotWrapper(strategy.getBot());
            name = strategy.getName();
        }
    }

    public void fromWrapper(Strategy strategy) {
        if (strategy != null) {
            strategy.setName(name);
        }
    }

}
