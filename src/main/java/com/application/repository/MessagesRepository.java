package com.application.repository;

import com.application.model.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

public interface MessagesRepository extends CrudRepository<Messages, Integer> {
    void save(MultipartFile image);
}
