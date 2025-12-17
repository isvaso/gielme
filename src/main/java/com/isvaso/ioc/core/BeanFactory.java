package com.isvaso.ioc.core;

public interface BeanFactory {

    <T> T getBean(Class<T> type);
}
