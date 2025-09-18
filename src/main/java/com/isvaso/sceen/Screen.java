package com.isvaso.sceen;

import com.isvaso.controller.Controller;
import com.isvaso.view.View;
import com.isvaso.view.IndexView;

import java.util.Scanner;

public class Screen {

    private final String FLUSH = "\u001B[H\u001B[2J";
    private final String TOP_OFFSET = "\n\n\n\n\n";
    private final String SINGLE_OFFSET = "\n";
    private View view;

    public Screen() {
        view = new IndexView();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(FLUSH);
            System.out.print(TOP_OFFSET);
            System.out.print(view.render());

            System.out.print(SINGLE_OFFSET);
            System.out.print("> ");
            String input = scanner.nextLine();

            Controller controller = view.getController();
            view = controller.handleCommand(input);
        }
    }
}
