package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.TaskListView;
import com.isvaso.view.View;

public class TaskCreateController extends BaseController {

    public TaskCreateController(View view) {
        super(view);
    }

    @Override
    protected View handleUserInput(String input) {
        if(input == null || input.isBlank())
            return view;
        service.add(new Task(input));
        return new TaskListView();
    }
}
