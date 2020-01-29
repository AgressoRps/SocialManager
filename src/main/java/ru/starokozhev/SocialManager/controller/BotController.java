package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.BotWrapper;
import ru.starokozhev.SocialManager.service.BotService;

import java.util.List;

@RestController
@RequestMapping("bot")
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping
    public BotWrapper add(BotWrapper wrapper) {
        return botService.add(wrapper);
    }

    @PatchMapping
    public BotWrapper edit(BotWrapper wrapper) {
        return botService.edit(wrapper);
    }

    @GetMapping("{name}")
    public BotWrapper get(@PathVariable String name) {
        return botService.get(name);
    }

    @DeleteMapping
    public void delete(String name) {
        botService.delete(name);
    }

    @GetMapping
    public List<BotWrapper> list() {
        return botService.list();
    }

}
