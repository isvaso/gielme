package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.View;

import java.util.List;

public class TaskSolveController extends BaseController {

    public TaskSolveController(View view) {
        super(view);
    }

    @Override
    protected View handleUserInput(String input) {
        if(!input.matches("\\d+"))
            return view;
        int index = Integer.parseInt(input);
        List<Task> tasks = service.get();
        if(index >= tasks.size() )
            return view;
        tasks.get(index).toggleState();
        service.update(tasks);
        return view;
    }
}
