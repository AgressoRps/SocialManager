package ru.starokozhev.SocialManager.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.User;

@Data
@NoArgsConstructor
public class RequestBotsFilter {

    private String user;
    private String key;

    public RequestBotsFilter(User user) {
        toWrapper(user);
    }

    private void toWrapper(User user) {
        if (user != null) {
            this.user = user.getVtopeUser();
            this.key = user.getVtopeKey();
        }
    }

}
