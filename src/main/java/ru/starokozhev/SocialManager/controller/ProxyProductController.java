package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.ProxyAccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.service.ProxyProductService;

import java.util.List;

@RestController
@RequestMapping("proxy")
@RequiredArgsConstructor
public class ProxyProductController {

    private final ProxyProductService proxyProductService;

    @PostMapping
    public ProxyAccountWrapper add(ProxyAccountWrapper wrapper) {
        return proxyProductService.add(wrapper);
    }

    @PatchMapping
    public ProxyAccountWrapper edit(ProxyAccountWrapper wrapper) {
        return proxyProductService.edit(wrapper);
    }

    @GetMapping("{id}")
    public ProxyAccountWrapper get(@PathVariable(name = "id") Long id) {
        return proxyProductService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "name") Long id) {
        proxyProductService.delete(id);
    }

    @GetMapping
    public List<ProxyAccountWrapper> list(ProxyAccountFilter filter) {
        return proxyProductService.list(filter);
    }

}
