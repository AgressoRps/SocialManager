package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Earned;
import ru.starokozhev.SocialManager.entity.VtopeBot;
import ru.starokozhev.SocialManager.enums.BotAccess;
import ru.starokozhev.SocialManager.enums.BotStatus;

@Data
@NoArgsConstructor
public class VtopeBotWrapper {

    private Long id;

    private BotStatus status;

    private String name;

    private BotAccess access;

    private EarnedWrapper earned;

    private AccountsWrapper accounts;

    private ProxiesWrapper proxies;

    public VtopeBotWrapper(VtopeBot bot) {
        toWrapper(bot);
    }

    private void toWrapper(VtopeBot bot) {
        if (bot != null) {
            id = bot.getId();
            status = bot.getStatus();
            name = bot.getName();
            access = bot.getAccess();
            earned = new EarnedWrapper(bot.getEarned());
            accounts = new AccountsWrapper(bot.getAccounts());
            proxies = new ProxiesWrapper(bot.getProxies());
        }
    }

    public void fromWrapper(VtopeBot bot) {
        if (bot != null) {
            bot.setStatus(status);
            bot.setName(name);
            bot.setAccess(access);
        }
    }

}
