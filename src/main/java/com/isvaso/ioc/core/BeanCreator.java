package com.isvaso.ioc.core;

@FunctionalInterface
public interface BeanCreator<T> {

    T create(BeanFactory beanFactory);
}
