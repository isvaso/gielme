package com.isvaso.view;

import com.isvaso.controller.TaskCreateController;

public class TaskCreateView extends BaseView {

    @Override
    public TaskCreateController getController() {
        return new TaskCreateController(this);
    }

    @Override
    public String render() {
        return LOGO +
               """
                   
                   
               Enter new task name
                   
               B. Back
               Q. Quit
               """;
    }
}
