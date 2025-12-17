package com.isvaso.ioc.configuration;

import com.isvaso.ioc.core.BeanContainer;
import com.isvaso.serialization.DataVersionSerializer;
import com.isvaso.serialization.TaskSerializer;

public class SerializationConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerPrototype(TaskSerializer.class, bc -> new TaskSerializer());
        beanContainer.registerPrototype(DataVersionSerializer.class, bc -> new DataVersionSerializer());
    }
}
