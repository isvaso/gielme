package com.isvaso.ioc;

public interface BeanFactory {

    <T> T getBean(Class<T> type);
}
