package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.MailWrapper;
import ru.starokozhev.SocialManager.entity.Mail;
import ru.starokozhev.SocialManager.repository.MailRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final UserService userService;

    public MailWrapper add(MailWrapper wrapper) {
        Mail mail = new Mail();

        Mail existMail = mailRepository.findByEmail(wrapper.getMail());
        if (existMail != null)
            throw new IllegalArgumentException("Почта уже существует!");

        wrapper.fromWrapper(mail);
        mail.setUser(userService.getCurrentUser());

        return new MailWrapper(mailRepository.save(mail));
    }

    public MailWrapper edit(MailWrapper wrapper) {
        Mail existMail = mailRepository.findByEmail(wrapper.getMail());

        if (existMail == null)
            throw new IllegalArgumentException("Почта не найдена!");

        wrapper.fromWrapper(existMail);

        return new MailWrapper(mailRepository.save(existMail));
    }

    public MailWrapper get(String mail) {
        Mail existMail = mailRepository.findByEmail(mail);

        if (existMail == null)
            throw new IllegalArgumentException("Почта не найдена!");

        return new MailWrapper(existMail);
    }

    public void delete(String mail) {
        Mail existMail = mailRepository.findByEmail(mail);

        if (existMail == null)
            throw new IllegalArgumentException("Почта не найден!");

        existMail.setDateClose(LocalDateTime.now());

        mailRepository.save(existMail);
    }

    public List<MailWrapper> list() {
        return mailRepository.findAllByUser(userService.getCurrentUser())
                .stream().map(MailWrapper::new).collect(Collectors.toList());
    }

}
