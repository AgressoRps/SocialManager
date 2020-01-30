package ru.starokozhev.SocialManager.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.dto.BotWrapper;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotWrapper;
import ru.starokozhev.SocialManager.entity.User;

@Data
@NoArgsConstructor
public class RequestDetailBot {

    private String user;
    private String key;
    private Long id;

    public RequestDetailBot(User user, VtopeBotWrapper bot) {
        toWrapper(user, bot);
    }

    private void toWrapper(User user, VtopeBotWrapper bot) {
        if (user != null && bot != null) {
            this.user = user.getVtopeUser();
            this.key = user.getVtopeKey();
            this.id = bot.getId();
        }
    }

}
