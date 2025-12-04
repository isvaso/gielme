package com.isvaso.ioc.configuration;

import com.isvaso.ioc.BeanContainer;

import java.util.List;

public class BeanConfigurationManager {

    private final List<BeanConfiguration> configs = buildConfigs();

    private List<BeanConfiguration> buildConfigs() {
        return List.of(
                new SerializationConfiguration(),
                new EncryptionConfiguration(),
                new StorageConfiguration(),
                new RepositoryConfiguration(),
                new ServiceConfiguration(),
                new MigrationConfiguration(),
                new ControllerConfiguration(),
                new ViewConfiguration(),
                new ScreenConfiguration()
        );
    }

    public void configure(BeanContainer beanContainer) {
        configs.forEach(config -> config.configure(beanContainer));
    }
}
