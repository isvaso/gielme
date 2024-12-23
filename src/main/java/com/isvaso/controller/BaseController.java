package com.isvaso.controller;

import com.isvaso.service.TaskService;
import com.isvaso.view.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class BaseController implements Controller {

    protected final TaskService service = TaskService.getInstance();

    protected final View view;

    @Override
    public View handleCommand(String command) {
        String commandUpperCase = command.toUpperCase();
        switch (commandUpperCase) {
            case "Q":
                System.exit(0);
            case "B":
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
