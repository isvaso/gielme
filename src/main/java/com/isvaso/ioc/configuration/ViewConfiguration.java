package com.isvaso.ioc.configuration;

import com.isvaso.ioc.core.BeanContainer;
import com.isvaso.ui.view.*;

public class ViewConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerPrototype(IndexView.class, bc -> new IndexView());
        beanContainer.registerPrototype(TaskCreateView.class, bc -> new TaskCreateView());
        beanContainer.registerPrototype(TaskDeleteView.class, bc -> new TaskDeleteView());
        beanContainer.registerPrototype(TaskListView.class, bc -> new TaskListView());
        beanContainer.registerPrototype(TaskSolveView.class, bc -> new TaskSolveView());
    }
}
