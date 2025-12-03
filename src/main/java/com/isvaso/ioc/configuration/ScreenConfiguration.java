package com.isvaso.ioc.configuration;

import com.isvaso.ioc.BeanContainer;
import com.isvaso.screen.Screen;
import com.isvaso.view.IndexView;

public class ScreenConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(Screen.class, bc -> new Screen());
    }
}
