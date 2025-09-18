package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.TaskListView;
import com.isvaso.view.View;

public class TaskCreateController extends BaseController {

    public TaskCreateController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        service.add(new Task(command));
        return new TaskListView();
    }
}
