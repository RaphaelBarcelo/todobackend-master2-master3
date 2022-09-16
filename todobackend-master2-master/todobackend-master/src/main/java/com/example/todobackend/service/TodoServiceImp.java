package com.example.todobackend.service;

import com.example.todobackend.data.TodoEntity;
import com.example.todobackend.view.TodoCreate;
import com.example.todobackend.view.TodoUpdate;
import com.example.todobackend.model.TodoModel;
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
        return StreamSupport.stream(todoRepository.findAll().spliterator(), false).map(this::convertEntityToModel).toList());
    }

    @Override
    public Optional<TodoModel> get(UUID id) {
        return todoRepository.findByUUID(id).map(this::convertEntityToModel);
    }

    @Override
    public TodoModel create(Boolean completed, String title, int order) {
        TodoEntity todoEntity = new TodoEntity(title, completed,  order);
        return convertEntityToModel(todoRepository.save(todoEntity));
    }

    @Override
    public Optional<TodoModel> update (UUID id, Boolean completed, String title, Integer order) {
        Optional<TodoEntity> todoEntity = todoRepository.findByUUID(id);
        return todoEntity
                .map(todo -> {
                    return new TodoEntity(
                            id,
                            title != null ? title : todo.getTitle(),
                            completed != null ? completed : todo.isCompleted(),
                            order != null ? order : todo.getOrder()
                    );
                })
                .map(updatedTodo -> {
                    return todoRepository.save(updatedTodo);
                })
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
        todoRepository.deleteByUUID(id);
    }

    private TodoModel convertEntityToModel(TodoEntity todoEntity){
        return new TodoModel(todoEntity.getId(), todoEntity.getTitle(), todoEntity.isCompleted(),  todoEntity.getOrder());
    }

    private TodoEntity patchWith(TodoEntity todoEntity, TodoUpdate todoUpdate) {
        if (todoUpdate.getTitle() != null) {
            todoEntity.setTitle(todoUpdate.getTitle());
        }

        if (todoUpdate.isCompleted() != null) {
            todoEntity.setCompleted(todoUpdate.isCompleted());
        }

        if (todoUpdate.getOrder() != null) {
            todoEntity.setOrder(todoUpdate.getOrder());
        }

        return todoEntity;
    }

}
