package ru.starokozhev.SocialManager.service.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.starokozhev.SocialManager.dto.filter.RequestBots;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotListWrapper;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotWrapper;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChangeFinderScheduler {

    private final RestTemplate restTemplate;
    private final SocialManagerBot socialManagerBot;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000)
    public void findChangesInVtopeBot() {
        List<User> updatedUsers = userRepository.findUsersByUpdateFromVtopeIsNotNull();

        if (updatedUsers != null && !updatedUsers.isEmpty()) {

            for (User user : updatedUsers) {
                if (user.getVtopeKey() != null && user.getVtopeUser() != null) {
                    RequestBots filter = new RequestBots();
                    filter.setKey(user.getVtopeKey());
                    filter.setUser(user.getVtopeUser());

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

                    log.info("VTOPE: bot list loaded");
                    if (responseEntity.getBody() != null) {
                        List<VtopeBotWrapper> vtopeBots = responseEntity.getBody().getBots();

                        //todo

                        //compareBots(responseEntity.getBody().getBots());
                    }
                }
            }

        }
    }

    private void compareBots(List<VtopeBotWrapper> responseBots) {
        /*for (int i = 0; i < oldBots.size(); i++) {
            for (int j = 0; j < responseBots.size(); j++) {

                if (oldBots.get(i).getId().equals(responseBots.get(j).getId())) {
                    if (!oldBots.get(i).equals(responseBots.get(j))) {
                        String message = buildMessage(responseBots.get(j));
                        socialManagerBot.sendMessage(message);
                    }
                }

            }
        }*/
    }

    private String buildMessage(VtopeBotWrapper bot) {
        StringBuffer messageBuilder = new StringBuffer();

        messageBuilder.append("................" + "\n");
        messageBuilder.append("БОТ: " + bot.getName() + "\n");
        messageBuilder.append("Статус: " + bot.getStatus().getFriendlyName() + "\n");
        messageBuilder.append("Состояние: " + bot.getAccess() + "\n");
        messageBuilder.append("===============" + "\n");
        messageBuilder.append("Заработано за день: " + bot.getEarned().getDay() + "\n");
        messageBuilder.append("Заработано за неделю: " + bot.getEarned().getWeek() + "\n");
        messageBuilder.append("Заработано за месяц: " + bot.getEarned().getMonth() + "\n");
        messageBuilder.append("===============" + "\n");
        messageBuilder.append("Кол-во аккаунтов в работе: " + bot.getAccounts().getSuccess() + "\n");
        messageBuilder.append("Кол-во аккаунтов с проблемами: " + bot.getAccounts().getWarning() + "\n");
        messageBuilder.append("Кол-во нерабочих аккаунтов: " + bot.getAccounts().getDanger() + "\n");
        messageBuilder.append("Кол-во аккаунтов, требующих вмешательство: " + bot.getAccounts().getPrimary() + "\n");
        messageBuilder.append("===============" + "\n");
        messageBuilder.append("Кол-во прокси в работе: " + bot.getProxies().getSuccess() + "\n");
        messageBuilder.append("Кол-во прокси с проблемами: " + bot.getProxies().getDanger() + "\n");
        messageBuilder.append("Кол-во нерабочих прокси: " + bot.getProxies().getWarning() + "\n");
        messageBuilder.append("................" + "\n");

        return messageBuilder.toString();
    }

}
