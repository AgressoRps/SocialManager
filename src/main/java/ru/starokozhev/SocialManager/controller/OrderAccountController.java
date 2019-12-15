package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.OrderAccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.OrderAccountFilter;
import ru.starokozhev.SocialManager.service.OrderAccountService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderAccountController {

    private final OrderAccountService orderAccountService;

    @PostMapping
    public OrderAccountWrapper add(OrderAccountWrapper wrapper) {
        return orderAccountService.add(wrapper);
    }

    @PatchMapping
    public OrderAccountWrapper edit(OrderAccountWrapper wrapper) {
        return orderAccountService.edit(wrapper);
    }

    @GetMapping("{id}")
    public OrderAccountWrapper get(@PathVariable(name = "id") Long id) {
        return orderAccountService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        orderAccountService.delete(id);
    }

    @GetMapping
    public List<OrderAccountWrapper> list(OrderAccountFilter filter) {
        return orderAccountService.list(filter);
    }

}
