package com.isvaso.ioc;

@FunctionalInterface
public interface BeanCreator<T> {

    T create(BeanFactory beanFactory);
}
