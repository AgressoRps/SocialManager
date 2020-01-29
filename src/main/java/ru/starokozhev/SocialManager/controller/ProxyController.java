package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.dto.filter.ProxyFilter;
import ru.starokozhev.SocialManager.service.ProxyService;

import java.util.List;

@RestController
@RequestMapping("proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyService;

    @PostMapping
    public ProxyWrapper add(ProxyWrapper wrapper) {
        return proxyService.add(wrapper);
    }

    @PatchMapping
    public ProxyWrapper edit(ProxyWrapper wrapper) {
        return proxyService.edit(wrapper);
    }

    @GetMapping("{id}")
    public ProxyWrapper get(Long id) {
        return proxyService.get(id);
    }

    @DeleteMapping
    public void delete(Long id) {
        proxyService.delete(id);
    }

    @GetMapping
    public List<ProxyWrapper> list(ProxyFilter filter) {
        return proxyService.list(filter);
    }

}
