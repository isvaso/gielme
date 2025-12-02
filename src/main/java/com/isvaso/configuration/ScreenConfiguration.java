package com.isvaso.configuration;

import com.isvaso.ioc.BeanContainer;
import com.isvaso.sceen.Screen;

public class ScreenConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(Screen.class, bc -> new Screen());
    }
}
