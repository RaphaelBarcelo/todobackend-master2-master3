package com.example.todobackend.service;

import com.example.todobackend.view.TodoCreate;
import com.example.todobackend.view.TodoUpdate;
import com.example.todobackend.model.TodoModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public interface TodoService {

    Iterable<TodoModel> getAll();
    Optional<TodoModel> get(UUID id);
    TodoModel create(Boolean completed, String title, int order);

    Optional<TodoModel> update (UUID id, Boolean completed, String title, Integer order);

    void deleteAll();

    void deleteByCompleted(Boolean completed);

    void delete(UUID id);
}
