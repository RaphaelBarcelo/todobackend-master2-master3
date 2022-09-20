package com.example.todobackend.domain.service;

import com.example.todobackend.data.TodoEntity;
import com.example.todobackend.domain.model.TodoModel;
import com.example.todobackend.data.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class TodoServiceImp implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImp(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoModel> getAll() {
        return StreamSupport.stream(todoRepository.findAll().spliterator(), false)
                .map(this::convertEntityToModel).toList();
    }

    @Override
    public Optional<TodoModel> get(UUID id) {
        return todoRepository.findById(id).map(this::convertEntityToModel);
    }

    @Override
    public TodoModel create(String title) {
        TodoEntity todoEntity = new TodoEntity(title, todoRepository.getMaxOrder() + 1);
        return convertEntityToModel(todoRepository.save(todoEntity));
    }

    @Override
    public Optional<TodoModel> update (UUID id, String title, Boolean completed, Integer order) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        return todoEntity
                .map(todo -> {
                    return new TodoEntity(
                            id,
                            title != null ? title : todo.getTitle(),
                            completed != null ? completed : todo.isCompleted(),
                            order != null ? order : todo.getOrder()
                    );
                })
                .map(todoRepository::save)
                .map(savedTodo -> new TodoModel());
    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();
    }

    @Override
    public void deleteByCompleted(Boolean completed) {
        todoRepository.deleteByCompleted(completed);
    }

    @Override
    public void delete(UUID id) {
        todoRepository.deleteById(id);
    }

    @Override
    public int getMaxOrder() {
        return todoRepository.getMaxOrder();
    }

    private TodoModel convertEntityToModel(TodoEntity todoEntity){
        return new TodoModel(todoEntity.getId(), todoEntity.getTitle(), todoEntity.isCompleted(),  todoEntity.getOrder());
    }
}