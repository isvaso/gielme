package com.isvaso.controller;

import com.isvaso.view.*;

public class IndexController extends BaseController {

    public IndexController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        String commandUpperCase = command.toUpperCase();
        switch (commandUpperCase) {
            case "L":
                return new TaskListView();
            case "C":
                return new TaskCreateView();
            case "S":
                return new TaskSolveView();
            case "D":
                return new TaskDeleteView();
            default:
                return view;
        }
    }
}
