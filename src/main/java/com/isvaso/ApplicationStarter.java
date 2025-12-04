package com.isvaso;

import com.isvaso.controller.IndexController;
import com.isvaso.ioc.configuration.BeanConfigurationManager;
import com.isvaso.exception.IocException;
import com.isvaso.exception.MigrationException;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.migration.MigrationManager;
import com.isvaso.screen.Screen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationStarter {

    public static void start() {
        try {
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

    private static BeanContainer buildContainer() {
        BeanContainer beanContainer = new BeanContainer();
        new BeanConfigurationManager().configure(beanContainer);
        beanContainer.initializeSingletons();
        return beanContainer;
    }

    private static void startApplication(BeanContainer container) {
        container.getBean(MigrationManager.class).run();
        IndexController indexController = container.getBean(IndexController.class);
        log.info("Gielme is working!");
        indexController.show();
    }
}
