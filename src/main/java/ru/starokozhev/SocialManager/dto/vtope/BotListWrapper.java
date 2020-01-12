package ru.starokozhev.SocialManager.dto.vtope;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BotListWrapper {

    private List<VtopeBotWrapper> bots;

    private VtopeEarnedWrapper earned;

}
