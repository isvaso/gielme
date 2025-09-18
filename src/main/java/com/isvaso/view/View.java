package com.isvaso.view;

import com.isvaso.controller.Controller;

public interface View {

    Controller getController();

    String render();
}
