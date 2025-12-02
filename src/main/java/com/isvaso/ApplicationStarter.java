package com.isvaso;

import com.isvaso.configuration.BeanConfigurationManager;
import com.isvaso.exception.IocException;
import com.isvaso.exception.MigrationException;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.migration.MigrationManager;
import com.isvaso.sceen.Screen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationStarter {

    public static void start() {
        try {
            runMigrations();
            BeanContainer container = buildContainer();
            startApplication(container);
        } catch (MigrationException migrationException) {
            log.error("Migrations failed. Gielme will be closed!", migrationException);
            System.exit(1);
        } catch (IocException iocException) {
            log.error("IoC initialization failed. Gielme will be closed!", iocException);
            System.exit(1);
        } catch (Exception exception) {
            log.error("Unexpected fatal error", exception);
            System.exit(1);
        }
    }

    private static void runMigrations() {
        MigrationManager.getInstance().run();
    }

    private static BeanContainer buildContainer() {
        BeanContainer beanContainer = new BeanContainer();
        new BeanConfigurationManager().configure(beanContainer);
        return beanContainer;
    }

    private static void startApplication(BeanContainer container) {
        Screen screen = container.getBean(Screen.class);
        log.info("Gielme is working!");
        screen.start();
    }
}
