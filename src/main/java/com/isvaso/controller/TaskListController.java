package com.isvaso.controller;

import com.isvaso.controller.Controller;
import com.isvaso.model.Task;
import com.isvaso.service.TaskService;
import com.isvaso.view.View;
import com.isvaso.view.IndexView;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskListController implements Controller {

    private final TaskService service = TaskService.getInstance();

    @Override
    public View handleInput(String input) {
        switch (input) {
            case "0":
                System.exit(0);
                break;
            case "1":
                return new IndexView();
        }
        return new IndexView();
    }

    public List<Task> getAll() {
        return service.get();
    }
}
