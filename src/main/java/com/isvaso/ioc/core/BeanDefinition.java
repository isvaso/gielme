package com.isvaso.ioc.core;

public record BeanDefinition<T> (
        Class<T> type,
        ScopeEnum scope,
        BeanCreator<T> creator
) {
}
