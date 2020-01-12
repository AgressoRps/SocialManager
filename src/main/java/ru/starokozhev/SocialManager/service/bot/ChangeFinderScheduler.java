package ru.starokozhev.SocialManager.service.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.starokozhev.SocialManager.dto.filter.RequestBotsFilter;
import ru.starokozhev.SocialManager.dto.vtope.VtopeBotListWrapper;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChangeFinderScheduler {

    //TODO changed
    private static String url = "https://vto.pe/botcontrol/list";

    private final RestTemplate restTemplate;

    private VtopeBotListWrapper previousRequestBots;

    @Scheduled(fixedRate = 60000)
    public void findChangesInVtopeBot() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<RequestBotsFilter> requestEntity = new HttpEntity<>(new RequestBotsFilter(), headers);

        ResponseEntity<VtopeBotListWrapper> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                VtopeBotListWrapper.class
        );

        log.info("Result: {}", responseEntity.getBody());

    }

}
