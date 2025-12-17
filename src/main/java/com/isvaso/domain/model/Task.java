package com.isvaso.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Task {

    private String name;
    private TaskState state;

    public Task(String name) {
        this.name = name;
        this.state = TaskState.UNSOLVED;
    }

    public void toggleState() {
        state = state.next();
    }
}
