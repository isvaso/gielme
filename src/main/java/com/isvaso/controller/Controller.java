package com.isvaso.controller;

import com.isvaso.view.View;

public interface Controller {

    View handleCommand(String input);
}
