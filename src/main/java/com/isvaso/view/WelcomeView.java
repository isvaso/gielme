package com.isvaso.view;

import com.isvaso.controller.Controller;
import com.isvaso.controller.WelcomeController;

public class WelcomeView implements View {

    @Override
    public Controller getController() {
        return new WelcomeController();
    }

    @Override
    public String render() {
        return """
               
               Welcome to Task Manager
               
               
               
               
               
               
               
               v 0.0.1
               
               """;
    }
}
