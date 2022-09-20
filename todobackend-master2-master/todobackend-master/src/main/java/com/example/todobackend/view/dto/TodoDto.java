package com.example.todobackend.view.dto;

import com.example.todobackend.domain.model.TodoModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class TodoDto {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private Boolean completed;
    private Integer order;
    private String url;

    public TodoDto() {
        this.completed = false;
    }

    public TodoDto(UUID id, String title, Boolean completed, Integer order, String url) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    public TodoDto(TodoModel todoModel, String url) {
        this.id = todoModel.getId();
        this.title = todoModel.getTitle();
        this.completed = todoModel.isCompleted();
        this.order = todoModel.getOrder();
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
