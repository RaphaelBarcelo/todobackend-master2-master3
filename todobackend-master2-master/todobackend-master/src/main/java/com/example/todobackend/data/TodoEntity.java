package com.example.todobackend.data;

import org.springframework.data.annotation.PersistenceCreator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TODO")
public class TodoEntity {
    private Boolean completed;
    private String title;
    @Column(name = "slot")
    private Integer order;
    @Id
    @GeneratedValue
    private UUID id;

    @PersistenceCreator
    public TodoEntity(UUID id, String title, Boolean completed, Integer order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
    }

    @PersistenceCreator
    public TodoEntity(String title, Integer order) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = false;
        this.order = order;
    }

    protected TodoEntity() {
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
