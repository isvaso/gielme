package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.View;

import java.util.List;

public class TaskSolveController extends BaseController {

    public TaskSolveController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        int index = Integer.parseInt(command);
        Task task = service.get(index);
        task.toggleState();
        return view;
    }

    public List<Task> getAll() {
        return service.get();
    }
}
