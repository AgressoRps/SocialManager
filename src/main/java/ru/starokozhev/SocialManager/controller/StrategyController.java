package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.StrategyWrapper;
import ru.starokozhev.SocialManager.dto.filter.StrategyFilter;
import ru.starokozhev.SocialManager.service.StrategyService;

import java.util.List;

@RestController
@RequestMapping("strategy")
@RequiredArgsConstructor
public class StrategyController {

    private final StrategyService strategyService;

    @PostMapping
    public StrategyWrapper add(StrategyWrapper wrapper) {
        return strategyService.add(wrapper);
    }

    @PatchMapping
    public StrategyWrapper edit(StrategyWrapper wrapper) {
        return strategyService.edit(wrapper);
    }

    @GetMapping("{name}")
    public StrategyWrapper get(@PathVariable String name) {
        return strategyService.get(name);
    }

    @DeleteMapping
    public void delete(String name) {
        strategyService.delete(name);
    }

    @GetMapping("list")
    public List<StrategyWrapper> list(StrategyFilter filter) {
        return strategyService.list(filter);
    }

}
