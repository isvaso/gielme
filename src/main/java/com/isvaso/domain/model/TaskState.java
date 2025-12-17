package com.isvaso.domain.model;

public enum TaskState {
    SOLVED,
    UNSOLVED;

    public TaskState next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
