package com.isvaso.ioc.configuration;

import com.isvaso.encryption.XorEncryptor;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.repository.DataVersionRepository;
import com.isvaso.repository.TaskRepository;
import com.isvaso.serialization.DataVersionSerializer;
import com.isvaso.serialization.TaskSerializer;
import com.isvaso.service.DataVersionService;
import com.isvaso.service.TaskBackupService;
import com.isvaso.service.TaskService;
import com.isvaso.storage.DataVersionStorage;
import com.isvaso.storage.FileManager;
import com.isvaso.storage.TaskStorage;

public class ServiceConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(
                DataVersionService.class,
                bc -> new DataVersionService(bc.getBean(DataVersionRepository.class))
        );
        beanContainer.registerSingleton(
                TaskBackupService.class,
                bc -> new TaskBackupService(bc.getBean(TaskStorage.class))
        );
        beanContainer.registerSingleton(
                TaskService.class,
                bc -> new TaskService(bc.getBean(TaskRepository.class))
        );
    }
}
