package com.isvaso.controller;

import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.IndexView;
import com.isvaso.view.View;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
abstract class BaseController implements Controller {

    protected final TaskService service;

    protected final View view;

    protected final Screen screen;

    @Override
    public void show() {
        screen.print(render());
        String cmd = screen.readLine();
        handleCommand(cmd);
    }

    protected abstract String render();

    public void handleCommand(String cmd) {
        Optional<CommandEnum> commandOptional = CommandEnum.getByKey(cmd);
        if (commandOptional.isEmpty())
            handleUserInput(cmd);
        switch (commandOptional.get()) {
            case QUIT:
                System.exit(0);
            case BACK:
                back();
            default:
                handleChoseCommand(cmd);
        }
    }

    protected abstract void back();

    protected void handleChoseCommand(String command) {
        show();
    }

    protected void handleUserInput(String input) {
        show();
    }
}
