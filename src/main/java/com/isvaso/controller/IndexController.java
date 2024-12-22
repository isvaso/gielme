package com.isvaso.controller;

import com.isvaso.view.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IndexController implements Controller {

    @Override
    public View handleInput(String input) {
        switch (input) {
            case "0":
                System.exit(0);
                break;
            case "1":
                return new TaskListView();
            case "2":
                return new TaskCreateView();
            case "3":
                return new TaskSolveView();
            case "4":
                break;
        }
        return new IndexView();
    }
}
