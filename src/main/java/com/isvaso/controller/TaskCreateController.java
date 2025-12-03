package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.TaskCreateView;
import com.isvaso.view.TaskListView;
import com.isvaso.view.View;
import lombok.RequiredArgsConstructor;

public class TaskCreateController extends BaseController {

    public TaskCreateController(
            TaskService service,
            View view,
            Screen screen
    ) {
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
        if (input == null || input.isBlank())
            show();
        service.add(new Task(input));
        show();
    }
}
