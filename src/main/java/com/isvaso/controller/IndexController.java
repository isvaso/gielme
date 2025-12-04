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
            Screen screen,
            Controller taskListController,
            Controller taskCreateController,
            Controller taskSolveController,
            Controller taskDeleteController) {
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
//                taskListController.show();
            case CREATE:
//                taskCreateController.show();
            case SOLVE:
//                taskSolveController.show();
            case DELETE:
//                taskDeleteController.show();
            default:
//                taskListController.show();
        }
    }
}
