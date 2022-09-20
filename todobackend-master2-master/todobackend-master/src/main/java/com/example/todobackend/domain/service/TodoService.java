package com.example.todobackend.domain.service;

import com.example.todobackend.domain.model.TodoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoService {

    List<TodoModel> getAll();
    Optional<TodoModel> get(UUID id);
    TodoModel create(String title);

    Optional<TodoModel> update (UUID id, String title, Boolean completed, Integer order);

    void deleteAll();

    void deleteByCompleted(Boolean completed);

    void delete(UUID id);

    int getMaxOrder();
}
