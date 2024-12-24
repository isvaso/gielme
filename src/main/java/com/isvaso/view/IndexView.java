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
                   
                   
               L. List
               C. Create
               S. Solve
               D. Delete
                   
               Q. Quit
               """;
    }
}
