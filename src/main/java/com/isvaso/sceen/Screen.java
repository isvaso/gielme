package com.isvaso.sceen;

import com.isvaso.controller.Controller;
import com.isvaso.controller.WelcomeController;
import com.isvaso.view.View;

import java.util.Scanner;

public class Screen {

    private final String FLUSH = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private Controller controller;

    public Screen() {
        controller = new WelcomeController();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            View view = controller.getView();
            System.out.println(FLUSH);
            System.out.println(view.render());

            System.out.print("> ");
            String input = scanner.nextLine();
            controller = controller.handleInput(input);
        }

    }
}
