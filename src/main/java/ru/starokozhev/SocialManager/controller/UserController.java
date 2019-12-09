package ru.starokozhev.SocialManager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.UserWrapper;
import ru.starokozhev.SocialManager.entity.Views;
import ru.starokozhev.SocialManager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    @JsonView(Views.AccessForRegister.class)
    public UserWrapper register(UserWrapper userWrapper) {
        return userService.add(userWrapper);
    }

    @PatchMapping
    @JsonView(Views.AccessForRegister.class)
    public UserWrapper edit(UserWrapper userWrapper) {
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
    public List<UserWrapper> list() {
        return userService.list();
    }


}
