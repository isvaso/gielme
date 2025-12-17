package com.isvaso.ioc.configuration;

import com.isvaso.ioc.core.BeanContainer;
import com.isvaso.ui.screen.Screen;

public class ScreenConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(Screen.class, bc -> new Screen());
    }
}
