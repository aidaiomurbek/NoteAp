package com.example.noteapp21.ui.models;

public class Task {
    private String name;
    private String fio;

    public Task() {
    }

    public Task(String name, String fio) {
        this.name = name;
        this.fio = fio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
