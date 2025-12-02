package com.isvaso.ioc;

public record BeanDefinition<T> (
        Class<T> type,
        ScopeEnum scope,
        BeanCreator<T> creator
) {
}
