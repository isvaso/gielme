package com.isvaso.view;

import com.isvaso.controller.IndexController;

public class IndexView extends BaseView {

    @Override
    public IndexController getController() {
        return new IndexController(this);
    }

    @Override
    public String render() {
        return LOGO +
               """
                   
                   
               1. List
               2. Create
               3. Solve
               4. Delete
                   
               Q. Quit
               """;
    }
}
