package com.isvaso.view;

import com.isvaso.controller.IndexController;

public class IndexView implements View {

    @Override
    public IndexController getController() {
        return new IndexController();
    }

    @Override
    public String render() {
        return
               """   
                        ██████╗ ██╗███████╗██╗     ███╗   ███╗███████╗
                       ██╔════╝ ██║██╔════╝██║     ████╗ ████║██╔════╝
                       ██║  ███╗██║█████╗  ██║     ██╔████╔██║█████╗ \s
                       ██║   ██║██║██╔══╝  ██║     ██║╚██╔╝██║██╔══╝ \s
                       ╚██████╔╝██║███████╗███████╗██║ ╚═╝ ██║███████╗
                        ╚═════╝ ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
                         TASK MANAGER                          v0.0.1
                   
                   
               Menu:
               1. List
               2. Create
               3. Solve
               4. Delete
                   
                   
               0. Exit
               """;
    }
}
