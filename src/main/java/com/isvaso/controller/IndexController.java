package com.isvaso.controller;

import com.isvaso.view.*;

public class IndexController extends BaseController {

    public IndexController(View view) {
        super(view);
    }

    @Override
    protected View handleChoseCommand(String command) {
        switch (command) {
            case "1":
                return new TaskListView();
            case "2":
                return new TaskCreateView();
            case "3":
                return new TaskSolveView();
            case "4":
                return new TaskDeleteView();
            default:
                return view;
        }
    }
}
