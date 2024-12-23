package com.isvaso.controller;

import com.isvaso.model.Task;
import com.isvaso.view.*;

import java.util.List;

public class TaskDeleteController extends BaseController {


    public TaskDeleteController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        int index = Integer.parseInt(command);
        service.delete(index);
        return view;
    }

    public List<Task> getAll() {
        return service.get();
    }
}
