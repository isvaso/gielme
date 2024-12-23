package com.isvaso.sceen;

import com.isvaso.controller.Controller;
import com.isvaso.view.View;
import com.isvaso.view.IndexView;

import java.util.Scanner;

public class Screen {

    private final String FLUSH = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private View view;

    public Screen() {
        view = new IndexView();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(FLUSH);
            System.out.println(view.render());

            System.out.print("> ");
            String input = scanner.nextLine();

            Controller controller = view.getController();
            // TODO: call input validation and show error message if false
            view = controller.handleCommand(input);
        }

    }
}
