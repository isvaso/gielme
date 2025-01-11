package com.isvaso;

import com.isvaso.sceen.Screen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        Screen screen = new Screen();
        log.info("Gielme is working!");
        screen.start();
    }
}
