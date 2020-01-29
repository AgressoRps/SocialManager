package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.starokozhev.SocialManager.dto.filter.RequestBotsFilter;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotListWrapper;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotWrapper;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class VtopeService {

    private final AccountService accountService;
    private final BotService botService;
    private final EarnedService earnedService;
    private final MailService mailService;
    private final ProxyService proxyService;
    private final StrategyService strategyService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    public void update() {
        User user = userService.getCurrentUser();

        if (user == null || user.getVtopeUser() == null || user.getVtopeKey() == null)
            throw new IllegalArgumentException("Не указан пользователь или ключ Vtope");





    }

    private List<VtopeBotWrapper> requestInfo(User user) {
        RequestBotsFilter filter = new RequestBotsFilter(user);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<RequestBotsFilter> requestEntity = new HttpEntity<>(filter, headers);

        String url = "https://vto.pe/botcontrol/list";
        ResponseEntity<VtopeBotListWrapper> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                VtopeBotListWrapper.class
        );

        log.info("VTOPE: bot list loaded");
        if (responseEntity.getBody() != null) {
            List<VtopeBotWrapper> vtopeBots = responseEntity.getBody().getBots();

            //todo

            //compareBots(responseEntity.getBody().getBots());
        }

        return null;
    }

}
