package ru.starokozhev.SocialManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.AccountWrapper;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public AccountWrapper add(AccountWrapper wrapper) {
        return accountService.add(wrapper);
    }

    @PatchMapping
    public AccountWrapper edit(AccountWrapper wrapper) {
        return accountService.edit(wrapper);
    }

    @GetMapping("{id}")
    public AccountWrapper get(@PathVariable(name = "id") Long id) {
        return accountService.get(id);
    }

    @PostMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        accountService.delete(id);
    }

    @GetMapping
    public List<AccountWrapper> list(AccountFilter filter) {
        return accountService.list(filter);
    }

}
