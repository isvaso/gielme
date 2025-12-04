package com.isvaso.ioc.configuration;

import com.isvaso.controller.*;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.*;

public class ControllerConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerSingleton(ControllerRegistry.class, bc -> new ControllerRegistry());
        beanContainer.registerSingleton(
                IndexController.class,
                bc -> new IndexController(
                        bc.getBean(ControllerRegistry.class),
                        bc.getBean(TaskService.class),
                        bc.getBean(IndexView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerSingleton(
                TaskListController.class,
                bc -> new TaskListController(
                        bc.getBean(ControllerRegistry.class),
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskListView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerSingleton(
                TaskCreateController.class,
                bc -> new TaskCreateController(
                        bc.getBean(ControllerRegistry.class),
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskCreateView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerSingleton(
                TaskSolveController.class,
                bc -> new TaskSolveController(
                        bc.getBean(ControllerRegistry.class),
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskSolveView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerSingleton(
                TaskDeleteController.class,
                bc -> new TaskDeleteController(
                        bc.getBean(ControllerRegistry.class),
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskDeleteView.class),
                        bc.getBean(Screen.class)
                )
        );
    }
}
