package com.tinotenda.task_tracker;

import java.time.LocalDateTime;

public class Task {

    final private int id;
    private String description;
    private String status;
    final private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id,String description,String status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.id=id;
        this.description=description;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    //Setters
    public void setStatus(String status){
        this.status=status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDescription(String description){
        this.description=description;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}