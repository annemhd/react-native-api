package com.application.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.model.Messages;
import com.application.repository.MessagesRepository;

import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessagesController {
    @Autowired

    @PostConstruct
    public void init() {
        // Create the "images" directory if it doesn't exist
        File directory = new File("images");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

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

    @PostMapping(path = "/image")
    public ResponseEntity<String> sendImage(@ModelAttribute Messages message,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        String fileName = file.getOriginalFilename();
        System.out.println("Uploaded image: " + fileName);

        message.setImage("/images/" + fileName);

        return new ResponseEntity<>("Image message sent successfully.", HttpStatus.OK);
    }
}
