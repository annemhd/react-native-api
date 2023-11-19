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

import com.application.model.Messages;
import com.application.repository.MessagesRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessagesController {
    @Autowired

    private MessagesRepository messageRepository;

    @GetMapping(path = "/messages")
    public @ResponseBody Iterable<Messages> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping(path = "/message/{id}")
    public @ResponseBody Optional<Messages> getMessage(@PathVariable Integer id) {
        return messageRepository.findById(id);
    }

    @PostMapping(path = "/message", consumes = { "*/*" })
    public Messages addNewMessage(@ModelAttribute Messages body) {
        messageRepository.save(body);
        return body;
    }

    @DeleteMapping(path = "/message/{id}/delete")
    public @ResponseBody String delUser(@PathVariable Integer id) {
        Messages message = messageRepository.findById(id).get();
        messageRepository.delete(message);
        return "Le message a bien été supprimé";
    }
}
