package com.isvaso;

import com.isvaso.exception.MigrationException;
import com.isvaso.migration.MigrationManager;
import com.isvaso.sceen.Screen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        try {
            MigrationManager.getInstance().run();
        } catch (MigrationException exception) {
            log.error("Migrations failed. Gielme will be closed!", exception);
            System.exit(1);
        }
        Screen screen = new Screen();
        log.info("Gielme is working!");
        screen.start();
    }
}
