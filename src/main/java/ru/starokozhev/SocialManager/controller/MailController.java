package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.MailWrapper;
import ru.starokozhev.SocialManager.service.MailService;

import java.util.List;

@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    public MailWrapper add(MailWrapper wrapper) {
        return mailService.add(wrapper);
    }

    @PatchMapping
    public MailWrapper edit(MailWrapper wrapper) {
        return mailService.edit(wrapper);
    }

    @GetMapping("{mail}")
    public MailWrapper get(@PathVariable String mail) {
        return mailService.get(mail);
    }

    @DeleteMapping
    public void delete(String mail) {
        mailService.delete(mail);
    }

    @GetMapping
    public List<MailWrapper> list() {
        return mailService.list();
    }

}
