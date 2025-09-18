package com.isvaso.controller;

import com.isvaso.view.*;

import java.util.Optional;

public class IndexController extends BaseController {

    public IndexController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        Optional<CommandEnum> commandOptional = CommandEnum.getByKey(command);
        if(commandOptional.isEmpty())
            return view;
        switch (commandOptional.get()) {
            case LIST:
                return new TaskListView();
            case CREATE:
                return new TaskCreateView();
            case SOLVE:
                return new TaskSolveView();
            case DELETE:
                return new TaskDeleteView();
            default:
                return view;
        }
    }
}
