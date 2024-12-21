package com.isvaso.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Task {

    private String name;
    private TaskState state;

    public Task(String name) {
        this.name = name;
    }
}
