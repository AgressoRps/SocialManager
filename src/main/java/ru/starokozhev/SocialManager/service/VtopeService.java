package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.starokozhev.SocialManager.dto.*;
import ru.starokozhev.SocialManager.dto.filter.RequestBots;
import ru.starokozhev.SocialManager.dto.filter.RequestDetailBot;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotListWrapper;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotWrapper;
import ru.starokozhev.SocialManager.entity.*;
import ru.starokozhev.SocialManager.repository.AccountRepository;
import ru.starokozhev.SocialManager.repository.BotRepository;
import ru.starokozhev.SocialManager.repository.ProxyRepository;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class VtopeService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final BotService botService;
    private final BotRepository botRepository;
    private final ProxyService proxyService;
    private final ProxyRepository proxyRepository;
    private final StrategyService strategyService;
    private final StrategyRepository strategyRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Scheduled(fixedRate = 60000)
    public void checkUpdates() {
        User user = userService.getCurrentUser();

        if (user != null)
            update(user);
    }

    private void update(User user) {
        if (user == null || user.getVtopeUser() == null || user.getVtopeKey() == null)
            throw new IllegalArgumentException("Не указан пользователь или ключ Vtope");

        List<VtopeBotWrapper> vtopeBots = requestBotsInfo(user);

        if (vtopeBots == null || vtopeBots.isEmpty())
            throw new IllegalArgumentException("Боты не найдены!");

        for (VtopeBotWrapper vtopeBotWrapper : vtopeBots) {
            BotWrapper bot = requestBotDetailInfo(user, vtopeBotWrapper);

            if (bot == null)
                throw new IllegalArgumentException("Информация о боте не найдена!");

            Bot existBot = botRepository.findByNameAndUser(bot.getName(), user);

            if (existBot != null)
                botService.add(bot);
            else
                botService.edit(bot);

            for (AccountWrapper accountWrapper : bot.getAccounts()) {
                Account existAccount = accountRepository.findByLoginAndUser(accountWrapper.getLogin(), user);

                if (existAccount != null)
                    accountService.edit(accountWrapper);
                else
                    accountService.add(accountWrapper);
            }

            for (ProxyWrapper proxyWrapper : bot.getProxies()) {
                Proxy existProxy = proxyRepository.findProxyByIpAndPortAndUser(proxyWrapper.getIp(),
                        proxyWrapper.getPort(), user);

                if (existProxy != null)
                    proxyService.edit(proxyWrapper);
                else
                    proxyService.add(proxyWrapper);
            }

            for (StrategyWrapper strategyWrapper : bot.getStrategies()) {
                Strategy existStrategy = strategyRepository.findStrategyByNameAndUser(strategyWrapper.getName(), user);

                if (existStrategy != null)
                    strategyService.edit(strategyWrapper);
                else
                    strategyService.add(strategyWrapper);
            }
        }

    }

    private List<VtopeBotWrapper> requestBotsInfo(User user) {
        RequestBots filter = new RequestBots(user);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<RequestBots> requestEntity = new HttpEntity<>(filter, headers);

        String url = "https://vto.pe/botcontrol/list";
        ResponseEntity<VtopeBotListWrapper> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                VtopeBotListWrapper.class
        );

        if (responseEntity.getBody() != null)
            return responseEntity.getBody().getBots();

        return null;
    }

    private BotWrapper requestBotDetailInfo(User user, VtopeBotWrapper vtopeBot) {
        RequestDetailBot filter = new RequestDetailBot(user, vtopeBot);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<RequestDetailBot> requestEntity = new HttpEntity<>(filter, headers);

        String url = "https://vto.pe/botcontrol/bot";
        ResponseEntity<BotWrapper> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                BotWrapper.class
        );

        if (requestEntity.getBody() != null)
            return responseEntity.getBody();

        return null;
    }

}
