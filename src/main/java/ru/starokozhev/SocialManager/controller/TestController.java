package ru.starokozhev.SocialManager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.starokozhev.SocialManager.dto.Views;

/*@RestController
@RequestMapping("message")
@RequiredArgsConstructor*/
public class TestController {

    /*private final MessageRepository messageRepository;

    @GetMapping
    @JsonView(Views.AccessList.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.AccessCard.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    @JsonView(Views.AccessCard.class)
    public Message create(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    @JsonView(Views.AccessCard.class)
    public Message update(@PathVariable("id") Message messageFromDb,
                                      @RequestBody Message message) {

        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    @JsonView(Views.AccessCard.class)
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }*/

}
