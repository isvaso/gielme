package com.isvaso.controller;

import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IndexController extends BaseController {

    public IndexController(
            ControllerRegistry registry,
            TaskService taskService,
            View view,
            Screen screen) {
        super(registry, taskService, view, screen);
    }

    @Override
    public ControllerNameEnum getName() {
        return ControllerNameEnum.INDEX;
    }

    @Override
    protected String render() {
        return view.render(new ArrayList<>());
    }

    @Override
    protected void back() {
        show();
    }

    @Override
    protected void handleChoseCommand(String cmd) {
        Optional<CommandEnum> commandOptional = CommandEnum.getByKey(cmd);
        if(commandOptional.isEmpty())
            show();
        switch (commandOptional.get()) {
            case LIST:
                registry.get(ControllerNameEnum.LIST).show();
            case CREATE:
                registry.get(ControllerNameEnum.CREATE).show();
            case SOLVE:
                registry.get(ControllerNameEnum.SOLVE).show();
            case DELETE:
                registry.get(ControllerNameEnum.DELETE).show();
            default:
                show();
        }
    }
}
