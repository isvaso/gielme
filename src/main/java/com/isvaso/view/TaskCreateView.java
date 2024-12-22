package com.isvaso.view;

import com.isvaso.controller.TaskCreateController;

public class TaskCreateView implements View {

    @Override
    public TaskCreateController getController() {
        return new TaskCreateController();
    }

    @Override
    public String render() {
       return """
               Enter new task name
               
               
               0. Exit
               """;
    }
}
