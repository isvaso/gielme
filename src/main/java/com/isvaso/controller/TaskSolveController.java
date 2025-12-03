package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.View;

import java.util.List;

public class TaskSolveController extends BaseController {

    public TaskSolveController(
            TaskService service,
            View view,
            Screen screen) {
        super(service, view, screen);
    }

    @Override
    protected String render() {
        return view.render(service.get());
    }

    @Override
    protected void back() {
       show();
    }

    @Override
    protected void handleUserInput(String input) {
        if (!input.matches("\\d+"))
            show();
        int index = Integer.parseInt(input);
        List<Task> tasks = service.get();
        if (index >= tasks.size())
            show();
        tasks.get(index).toggleState();
        service.update(tasks);
        show();
    }
}
