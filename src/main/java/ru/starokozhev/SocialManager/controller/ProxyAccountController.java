package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.ProxyAccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.service.ProxyAccountService;

import java.util.List;

@RestController
@RequestMapping("proxy")
@RequiredArgsConstructor
public class ProxyAccountController {

    private final ProxyAccountService proxyAccountService;

    @PostMapping
    public ProxyAccountWrapper add(ProxyAccountWrapper wrapper) {
        return proxyAccountService.add(wrapper);
    }

    @PatchMapping
    public ProxyAccountWrapper edit(ProxyAccountWrapper wrapper) {
        return proxyAccountService.edit(wrapper);
    }

    @GetMapping("{id}")
    public ProxyAccountWrapper get(@PathVariable(name = "id") Long id) {
        return proxyAccountService.get(id);
    }

    @PostMapping("{id}")
    public void delete(@PathVariable(name = "name") Long id) {
        proxyAccountService.delete(id);
    }

    @GetMapping
    public List<ProxyAccountWrapper> list(ProxyAccountFilter filter) {
        return proxyAccountService.list(filter);
    }

}
