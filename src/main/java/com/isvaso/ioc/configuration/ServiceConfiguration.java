package com.isvaso.ioc.configuration;

import com.isvaso.files.FileManager;
import com.isvaso.ioc.core.BeanContainer;
import com.isvaso.domain.repository.DataVersionRepository;
import com.isvaso.domain.repository.TaskRepository;
import com.isvaso.domain.service.DataVersionService;
import com.isvaso.backup.TaskBackupManager;
import com.isvaso.domain.service.TaskService;
import com.isvaso.storage.TaskStorage;

public class ServiceConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(
                DataVersionService.class,
                bc -> new DataVersionService(bc.getBean(DataVersionRepository.class))
        );
        beanContainer.registerSingleton(
                TaskBackupManager.class,
                bc -> new TaskBackupManager(bc.getBean(FileManager.class))
        );
        beanContainer.registerSingleton(
                TaskService.class,
                bc -> new TaskService(bc.getBean(TaskRepository.class))
        );
    }
}
