package com.isvaso.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

    private String name;
    private TaskState state;

    public Task(String name) {
        this.name = name;
    }
}
