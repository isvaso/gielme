package com.isvaso.controller;

import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.TaskListView;
import com.isvaso.view.View;

public class TaskListController extends BaseController {

    public TaskListController(
            ControllerRegistry registry,
            TaskService service,
            View view, Screen screen
    ) {
        super(registry, service, view, screen);
    }

    @Override
    public ControllerNameEnum getName() {
        return ControllerNameEnum.LIST;
    }

    @Override
    protected String render() {
        return view.render(service.get());
    }

    @Override
    protected void back() {
       show();
    }
}
