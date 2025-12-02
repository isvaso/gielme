package com.isvaso.configuration;

import com.isvaso.ioc.BeanContainer;

import java.util.List;

public class BeanConfigurationManager {

    private final List<BeanConfiguration> configs = buildConfigs();

    public BeanConfigurationManager() {
    }

    private List<BeanConfiguration> buildConfigs() {
        return List.of(
                new ScreenConfiguration()
        );
    }

    public void configure(BeanContainer beanContainer) {
        configs.forEach(config -> config.configure(beanContainer));
    }
}
