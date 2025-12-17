package com.isvaso.ioc.configuration;

import com.isvaso.ioc.core.BeanContainer;
import com.isvaso.migration.MigrationManager;
import com.isvaso.domain.service.DataVersionService;
import com.isvaso.backup.TaskBackupManager;

public class MigrationConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(
                MigrationManager.class,
                bc -> new MigrationManager(
                        bc.getBean(TaskBackupManager.class),
                        bc.getBean(DataVersionService.class)
                )
        );
    }
}