package com.isvaso.ui.controller;

import com.isvaso.domain.model.Task;
import com.isvaso.ui.screen.Screen;
import com.isvaso.domain.service.TaskService;
import com.isvaso.ui.view.View;

import java.util.List;

public class TaskSolveController extends BaseController {

    public TaskSolveController(
            ControllerRegistry registry,
            TaskService service,
            View view,
            Screen screen) {
        super(registry, service, view, screen);
    }

    @Override
    public ControllerNameEnum getName() {
        return ControllerNameEnum.SOLVE;
    }

    @Override
    protected String render() {
        return view.render(service.get());
    }

    @Override
    protected void back() {
        registry.get(ControllerNameEnum.INDEX).show();
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
