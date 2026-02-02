package com.example.TodoApplicationBackend.todoDto;


import lombok.Data;

@Data

public class TodoDto {

    private int id;

    private String title;

    private String description;

    private boolean isCompleted;

    public TodoDto() {

    }

    public TodoDto(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }


    public TodoDto(int id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public TodoDto(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
