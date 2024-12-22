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

    private TaskService service = new TaskService();

    @Override
    public View handleInput(String input) {
        int index = Integer.parseInt(input) - 2;
        Task task = service.get(index);
        int countOfStates = TaskState.values().length;
        int newStateIndex = (task.getState().ordinal() + 1) % countOfStates;
        TaskState newState = TaskState.values()[newStateIndex];
        task.setState(newState);
        return new TaskSolveView();
    }

    public List<Task> getAll() {
        return service.get();
    }
}
