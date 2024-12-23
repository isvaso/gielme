package com.isvaso.view;

import com.isvaso.controller.TaskCreateController;

public class TaskCreateView implements View {

    @Override
    public TaskCreateController getController() {
        return new TaskCreateController(this);
    }

    @Override
    public String render() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Enter new task name").append("\n").append("\n").append("B. Back").append("\n").append("Q. Quit").append("\n");
        return stringBuilder.toString();
    }
}
