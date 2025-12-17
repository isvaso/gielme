package com.isvaso.ui.screen;

import java.util.Scanner;

public class Screen {

    private final String FLUSH = "\u001B[H\u001B[2J";
    private final String TOP_OFFSET = "\n\n\n\n\n";
    private final String SINGLE_OFFSET = "\n";

    public void print(String string) {
        System.out.print(FLUSH);
        System.out.print(TOP_OFFSET);
        System.out.print(string);

        System.out.print(SINGLE_OFFSET);
        System.out.print("> ");
    }

    public String readLine() {
        return new Scanner(System.in).nextLine();
    }
}
