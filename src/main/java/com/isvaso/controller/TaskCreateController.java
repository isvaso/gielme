package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.service.TaskService;
import com.isvaso.view.View;
import com.isvaso.view.TaskListView;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskCreateController implements Controller {

    private final TaskService service = TaskService.getInstance();

    @Override
    public View handleInput(String input) {
        switch (input) {
            case "0":
                System.exit(0);
                break;
        }
        service.add(new Task(input));
        return new TaskListView();
    }
}
