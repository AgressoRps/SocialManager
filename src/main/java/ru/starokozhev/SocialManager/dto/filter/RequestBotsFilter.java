package ru.starokozhev.SocialManager.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestBotsFilter {

    private String user;
    private String key;

}
