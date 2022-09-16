package com.example.todobackend.view;

import java.util.UUID;

public class TodoCreate {
    private Boolean completed;
    private String title;
    private Integer order;
    private UUID id;
    private String url;

    public TodoCreate(Boolean completed, String title, Integer order, UUID id) {
        this.completed = completed;
        this.title = title;
        this.order = order;
        this.id = id;
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

