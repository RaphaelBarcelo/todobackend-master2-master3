package com.example.todobackend.domain.model;

import com.example.todobackend.data.TodoEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URI;
import java.util.UUID;

public class TodoModel {
    private Boolean completed;
    private String title;
    private Integer order;
    @Id
    @GeneratedValue
    private UUID id;

    public TodoModel() {
        this.completed = false;
    }

    public TodoModel(String title, Boolean completed, Integer order) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = completed;
        this.order = order;
    }

    public TodoModel(UUID id, String title, Boolean completed, Integer order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


}
