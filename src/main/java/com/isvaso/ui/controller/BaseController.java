package com.isvaso.ui.controller;

import com.isvaso.ui.screen.Screen;
import com.isvaso.domain.service.TaskService;
import com.isvaso.ui.view.View;

import java.util.Optional;

abstract class BaseController implements Controller {

    protected final ControllerRegistry registry;

    protected final TaskService service;

    protected final View view;

    protected final Screen screen;

    public BaseController(
            ControllerRegistry registry,
            TaskService service,
            View view,
            Screen screen
    ) {
        this.registry = registry;
        this.service = service;
        this.view = view;
        this.screen = screen;
        register();
    }

    protected void register() {
        registry.add(getName(), this);
    }

    protected abstract ControllerNameEnum getName();

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
