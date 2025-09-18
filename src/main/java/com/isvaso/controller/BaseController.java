package com.isvaso.controller;

import com.isvaso.service.TaskService;
import com.isvaso.view.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
abstract class BaseController implements Controller {

    protected final TaskService service = TaskService.getInstance();

    protected final View view;

    @Override
    public View handleCommand(String command) {
        Optional<CommandEnum> commandOptional = CommandEnum.getByKey(command);
        if(commandOptional.isEmpty())
            return view;
        switch (commandOptional.get()) {
            case QUIT:
                System.exit(0);
            case BACK:
                return back();
            default:
                return handleChoseCommand(command);
        }
    }

    protected View handleChoseCommand(String command) {
        return view;
    }

    protected View back() {
        return new IndexView();
    }
}
