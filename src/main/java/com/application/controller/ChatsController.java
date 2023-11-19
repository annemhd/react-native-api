package com.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Chats;
import com.application.repository.ChatsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatsController {
    @Autowired

    private ChatsRepository chatRepository;

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<Chats> getAllChats() {
        return chatRepository.findAll();
    }

    @GetMapping(path = "/chat/{id}")
    public @ResponseBody Optional<Chats> getChat(@PathVariable Integer id) {
        return chatRepository.findById(id);
    }

    @PostMapping(path = "/chat", consumes = { "*/*" })
    public Chats addChat(@ModelAttribute Chats body) {
        chatRepository.save(body);
        return body;
    }

    @DeleteMapping(path = "/chat/{id}/delete")
    public @ResponseBody String delChat(@PathVariable Integer id) {
        Chats chat = chatRepository.findById(id).get();
        chatRepository.delete(chat);
        return "La conversation a bien été supprimé";
    }
}
