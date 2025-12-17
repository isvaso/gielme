package com.isvaso.ui.controller;

import com.isvaso.domain.model.Task;
import com.isvaso.ui.screen.Screen;
import com.isvaso.domain.service.TaskService;
import com.isvaso.ui.view.View;

public class TaskCreateController extends BaseController {

    public TaskCreateController(
            ControllerRegistry registry,
            TaskService service,
            View view,
            Screen screen
    ) {
        super(registry, service, view, screen);
    }

    @Override
    public ControllerNameEnum getName() {
        return ControllerNameEnum.CREATE;
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
        if (input == null || input.isBlank())
            show();
        service.add(new Task(input));
        show();
    }


}
