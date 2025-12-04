package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.TaskDeleteView;
import com.isvaso.view.View;

import java.util.List;

public class TaskDeleteController extends BaseController {

    public TaskDeleteController(
            ControllerRegistry registry,
            TaskService service,
            View view,
            Screen screen
    ) {
        super(registry, service, view, screen);
    }

    @Override
    public ControllerNameEnum getName() {
        return ControllerNameEnum.DELETE;
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
        tasks.remove(index);
        service.update(tasks);
        show();
    }
}
