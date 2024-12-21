package com.isvaso.controller;

import com.isvaso.view.View;
import com.isvaso.view.WelcomeView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WelcomeController implements Controller {

    @Override
    public View handleInput(String input) {
        return new WelcomeView();
    }
}
