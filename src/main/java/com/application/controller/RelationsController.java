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

import com.application.model.Relations;
import com.application.repository.RelationsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RelationsController {
    @Autowired

    private RelationsRepository relationRepository;

    @GetMapping("/relations")
    public @ResponseBody Iterable<Relations> getAllRelations() {
        return relationRepository.findAll();
    }

    @GetMapping(path = "/relation/{id}")
    public @ResponseBody Optional<Relations> getRelation(@PathVariable Integer id) {
        return relationRepository.findById(id);
    }

    @PostMapping(path = "/relation", consumes = { "*/*" })
    public Relations addRelation(@ModelAttribute Relations body) {
        relationRepository.save(body);
        return body;
    }

    @DeleteMapping(path = "/relation/{id}/delete")
    public @ResponseBody String delRelation(@PathVariable Integer id) {
        Relations relation = relationRepository.findById(id).get();
        relationRepository.delete(relation);
        return "Le contact a bien été supprimé";
    }
}
