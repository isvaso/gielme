package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import com.isvaso.service.TaskService;
import com.isvaso.view.IndexView;
import com.isvaso.view.TaskSolveView;
import com.isvaso.view.View;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskSolveController implements Controller {

    private final TaskService service = TaskService.getInstance();

    @Override
    public View handleInput(String input) {
        int index = Integer.parseInt(input) - 2;
        Task task = service.get(index);
        task.toggleState();
        return new TaskSolveView();
    }

    public List<Task> getAll() {
        return service.get();
    }
}
