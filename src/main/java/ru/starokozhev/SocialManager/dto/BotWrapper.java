package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotAccess;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BotWrapper {

    private Long id;

    private VtopeBotStatus status;

    private String name;

    private VtopeBotAccess access;

    private Boolean earning;

    private Boolean allowControl;

    private EarnedWrapper earned;

    private List<AccountWrapper> accounts = new ArrayList<>();

    private List<ProxyWrapper> proxies = new ArrayList<>();

    private List<StrategyWrapper> strategies = new ArrayList<>();

    public BotWrapper(Bot bot) {
        toWrapper(bot);
    }

    private void toWrapper(Bot bot) {
        if (bot != null) {
            id = bot.getId();
            status = bot.getStatus();
            name = bot.getName();
            access = bot.getAccess();
            earning = bot.getEarning();
            allowControl = bot.getAllowControl();
            earned = new EarnedWrapper(bot.getEarned());
            accounts = bot.getAccounts().stream().map(AccountWrapper::new).collect(Collectors.toList());
            proxies = bot.getProxies().stream().map(ProxyWrapper::new).collect(Collectors.toList());
            strategies = bot.getStrategies().stream().map(StrategyWrapper::new).collect(Collectors.toList());
        }
    }

    public void fromWrapper(Bot bot) {
        if (bot != null) {
            bot.setStatus(status);
            bot.setName(name);
            bot.setAccess(access);
            bot.setEarning(earning);
            bot.setAllowControl(allowControl);
        }
    }

}
