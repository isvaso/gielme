package com.isvaso.model;

public enum TaskState {
    SOLVED,
    UNSOLVED;

    public TaskState next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
