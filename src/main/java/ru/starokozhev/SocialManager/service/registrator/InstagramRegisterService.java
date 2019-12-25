package ru.starokozhev.SocialManager.service.registrator;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.starokozhev.SocialManager.dto.AccountWrapper;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class InstagramRegisterService implements IRegister {

    private String TEMPORARY_MAIL_URL = "https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/7DBC340D48CB5C59C5E159E3FD9D5A20/";

    private final RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public List<AccountWrapper> register(Long count) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(TEMPORARY_MAIL_URL)
                .get()
                .addHeader("x-rapidapi-host", "privatix-temp-mail-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "a8362946b2msh0ad0cabf5e5b290p16a64djsnd01197f4d6d5")
                .build();

        Response response = client.newCall(request).execute();

        log.error("RESPONSE: " + response);
        return null;
    }
}
