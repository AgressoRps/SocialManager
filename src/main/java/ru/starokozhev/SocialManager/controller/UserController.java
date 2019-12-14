package ru.starokozhev.SocialManager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.UserWrapper;
import ru.starokozhev.SocialManager.dto.Views;
import ru.starokozhev.SocialManager.dto.filter.UserFilter;
import ru.starokozhev.SocialManager.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    @JsonView(Views.AccessForRegister.class)
    public UserWrapper register(@Valid UserWrapper userWrapper) {
        return userService.add(userWrapper);
    }

    @PatchMapping
    @JsonView(Views.AccessForRegister.class)
    public UserWrapper edit(@Valid UserWrapper userWrapper) {
        return userService.edit(userWrapper);
    }

    @GetMapping("{id}")
    public UserWrapper get(@PathVariable(name = "id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

    @GetMapping
    public List<UserWrapper> list(UserFilter filter) {
        return userService.list(filter);
    }


}
