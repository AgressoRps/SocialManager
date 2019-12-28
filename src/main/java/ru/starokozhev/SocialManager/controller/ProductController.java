package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.ProductWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductWrapper add(ProductWrapper wrapper) {
        return productService.add(wrapper);
    }

    @PatchMapping
    public ProductWrapper edit(ProductWrapper wrapper) {
        return productService.edit(wrapper);
    }

    @GetMapping("{id}")
    public ProductWrapper get(@PathVariable(name = "id") Long id) {
        return productService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }

    @GetMapping
    public List<ProductWrapper> list(AccountFilter filter) {
        return productService.list(filter);
    }

}
