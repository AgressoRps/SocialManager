package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.MailWrapper;
import ru.starokozhev.SocialManager.dto.filter.MailFilter;
import ru.starokozhev.SocialManager.repository.MailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;

    public MailWrapper add(MailWrapper wrapper) {
        return null;
    }

    public MailWrapper edit(MailWrapper wrapper) {
        return null;
    }

    public MailWrapper get(String mail) {
        return null;
    }

    public void delete(String mail) {

    }

    public List<MailWrapper> list(MailFilter filter) {
        return null;
    }

}
