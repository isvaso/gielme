package com.isvaso.sceen;

import com.isvaso.controller.Controller;
import com.isvaso.controller.WelcomeController;
import com.isvaso.view.View;
import com.isvaso.view.WelcomeView;

import java.util.Scanner;

public class Screen {

    private final String FLUSH = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private View view;

    public Screen() {
        view = new WelcomeView();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(FLUSH);
            System.out.println(view.render());

            System.out.print("> ");
            String input = scanner.nextLine();

            Controller controller = view.getController();
            view = controller.handleInput(input);
        }

    }
}
