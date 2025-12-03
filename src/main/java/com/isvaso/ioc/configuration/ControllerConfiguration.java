package com.isvaso.ioc.configuration;

import com.isvaso.controller.*;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.screen.Screen;
import com.isvaso.service.TaskService;
import com.isvaso.view.*;

public class ControllerConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerPrototype(
                IndexController.class,
                bc -> new IndexController(
                        bc.getBean(TaskService.class),
                        bc.getBean(IndexView.class),
                        bc.getBean(Screen.class),
                        bc.getBean(TaskListController.class),
                        bc.getBean(TaskCreateController.class),
                        bc.getBean(TaskSolveController.class),
                        bc.getBean(TaskDeleteController.class)
                )
        );
        beanContainer.registerPrototype(
                TaskListController.class,
                bc -> new TaskListController(
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskListView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerPrototype(
                TaskCreateController.class,
                bc -> new TaskCreateController(
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskCreateView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerPrototype(
                TaskSolveController.class,
                bc -> new TaskSolveController(
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskSolveView.class),
                        bc.getBean(Screen.class)
                )
        );
        beanContainer.registerPrototype(
                TaskDeleteController.class,
                bc -> new TaskDeleteController(
                        bc.getBean(TaskService.class),
                        bc.getBean(TaskDeleteView.class),
                        bc.getBean(Screen.class)
                )
        );
    }
}
