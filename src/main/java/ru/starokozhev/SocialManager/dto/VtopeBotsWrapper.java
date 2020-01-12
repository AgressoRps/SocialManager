package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VtopeBotsWrapper {

    private List<VtopeBotWrapper> bots;

    private EarnedWrapper earned;

}
