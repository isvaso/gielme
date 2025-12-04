package com.isvaso.ioc.configuration;

import com.isvaso.ioc.BeanContainer;
import com.isvaso.migration.MigrationManager;
import com.isvaso.service.DataVersionService;
import com.isvaso.service.TaskBackupService;

public class MigrationConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(
                MigrationManager.class,
                bc -> new MigrationManager(
                        bc.getBean(TaskBackupService.class),
                        bc.getBean(DataVersionService.class)
                )
        );
    }
}