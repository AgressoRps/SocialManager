package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.service.ProxyService;

import java.util.List;

@RestController
@RequestMapping("proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyProductService;

    @PostMapping
    public ProxyWrapper add(ProxyWrapper wrapper) {
        return proxyProductService.add(wrapper);
    }

    @PatchMapping
    public ProxyWrapper edit(ProxyWrapper wrapper) {
        return proxyProductService.edit(wrapper);
    }

    @GetMapping("{id}")
    public ProxyWrapper get(@PathVariable(name = "id") Long id) {
        return proxyProductService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "name") Long id) {
        proxyProductService.delete(id);
    }

    @GetMapping
    public List<ProxyWrapper> list(ProxyAccountFilter filter) {
        return proxyProductService.list(filter);
    }

}
