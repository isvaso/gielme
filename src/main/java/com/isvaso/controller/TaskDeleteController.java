package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.*;

import java.util.List;

public class TaskDeleteController extends BaseController {

    public TaskDeleteController(View view) {
        super(view);
    }

    @Override
    protected View handleUserInput(String input) {
        if(!input.matches("\\d+"))
            return view;
        int index = Integer.parseInt(input);
        List<Task> tasks = service.get();
        if(index >= tasks.size())
            return view;
        tasks.remove(index);
        service.update(tasks);
        return view;
    }
}
