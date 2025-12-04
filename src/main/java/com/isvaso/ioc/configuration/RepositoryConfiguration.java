package com.isvaso.ioc.configuration;

import com.isvaso.ioc.BeanContainer;
import com.isvaso.repository.DataVersionRepository;
import com.isvaso.repository.TaskRepository;
import com.isvaso.storage.DataVersionStorage;
import com.isvaso.storage.TaskStorage;

public class RepositoryConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(
                DataVersionRepository.class,
                bc -> new DataVersionRepository(bc.getBean(DataVersionStorage.class))
        );
        beanContainer.registerSingleton(
                TaskRepository.class, bc -> new TaskRepository(bc.getBean(TaskStorage.class)));
    }
}
