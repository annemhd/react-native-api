package com.application.repository;

import com.application.model.Chats;
import org.springframework.data.repository.CrudRepository;

public interface ChatsRepository extends CrudRepository<Chats, Integer> {

}