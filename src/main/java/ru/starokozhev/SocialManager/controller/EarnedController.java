package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.EarnedWrapper;
import ru.starokozhev.SocialManager.service.EarnedService;


@RestController
@RequestMapping("earned")
@RequiredArgsConstructor
public class EarnedController {

    private final EarnedService earnedService;

    @PostMapping
    public EarnedWrapper add(EarnedWrapper wrapper) {
        return earnedService.add(wrapper);
    }

    @PatchMapping
    public EarnedWrapper edit(EarnedWrapper wrapper) {
        return earnedService.edit(wrapper);
    }

    @GetMapping("{id}")
    public EarnedWrapper get(@PathVariable Long id) {
        return earnedService.get(id);
    }

}
