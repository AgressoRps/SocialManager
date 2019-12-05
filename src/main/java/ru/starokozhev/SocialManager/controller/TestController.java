package ru.starokozhev.SocialManager.controller;

import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.exceptions.TestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping
    public List list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> get(@PathVariable(name = "id") String id) {
        return messages.get(Integer.parseInt(id));
    }

    @PostMapping
    public String add(@RequestBody String body) {
        return body;
    }

    @PatchMapping
    public String edit(@RequestBody String body) {
        return body;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {

    }

}
