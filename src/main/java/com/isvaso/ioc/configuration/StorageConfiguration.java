package com.isvaso.ioc.configuration;

import com.isvaso.encryption.XorEncryptor;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.serialization.DataVersionSerializer;
import com.isvaso.serialization.TaskSerializer;
import com.isvaso.storage.DataVersionStorage;
import com.isvaso.storage.FileManager;
import com.isvaso.storage.TaskStorage;

public class StorageConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(FileManager.class, bc -> new FileManager());
        beanContainer.registerSingleton(
                DataVersionStorage.class,
                bc -> new DataVersionStorage(
                        bc.getBean(FileManager.class),
                        bc.getBean(DataVersionSerializer.class),
                        bc.getBean(XorEncryptor.class)
                )
        );
        beanContainer.registerSingleton(
                TaskStorage.class,
                bc -> new TaskStorage(
                        bc.getBean(FileManager.class),
                        bc.getBean(TaskSerializer.class),
                        bc.getBean(XorEncryptor.class)
                )
        );
    }
}
