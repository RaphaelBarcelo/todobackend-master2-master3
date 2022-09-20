package com.example.todobackend.view.dto;

import java.util.UUID;

public class TodoCreate {
    private Boolean completed;
    private String title;
    private Integer order;
    private UUID id;
    private String url;

    public TodoCreate(String title, Integer order) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = false;
        this.order = order;
    }

    public TodoCreate(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = false;
        this.order = 0;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

