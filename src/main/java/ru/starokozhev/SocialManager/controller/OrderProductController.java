package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.OrderProductWrapper;
import ru.starokozhev.SocialManager.dto.OrderWrapper;
import ru.starokozhev.SocialManager.dto.filter.OrderAccountFilter;
import ru.starokozhev.SocialManager.service.OrderProductService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;

    @PostMapping
    public OrderProductWrapper add(OrderProductWrapper wrapper) {
        return orderProductService.add(wrapper);
    }

    @PostMapping("/register")
    public OrderProductWrapper register(OrderWrapper wrapper) {
        return orderProductService.register(wrapper);
    }

    @PatchMapping
    public OrderProductWrapper edit(OrderProductWrapper wrapper) {
        return orderProductService.edit(wrapper);
    }

    @GetMapping("{id}")
    public OrderProductWrapper get(@PathVariable(name = "id") Long id) {
        return orderProductService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        orderProductService.delete(id);
    }

    @GetMapping
    public List<OrderProductWrapper> list(OrderAccountFilter filter) {
        return orderProductService.list(filter);
    }

}
