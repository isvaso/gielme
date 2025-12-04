package com.isvaso.ioc;

import com.isvaso.exception.IocException;
import com.isvaso.util.OptionalMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class BeanContainer implements BeanFactory {

    private final OptionalMap<Class<?>, BeanDefinition<?>> definitions = new OptionalMap<>();
    private final OptionalMap<Class<?>, Object> singletonCache = new OptionalMap<>();

    public <T> void registerSingleton(Class<T> type, BeanCreator<T> creator) {
        register(type, ScopeEnum.SINGLETON, creator);
    }

    public <T> void registerPrototype(Class<T> type, BeanCreator<T> creator) {
        register(type, ScopeEnum.PROTOTYPE, creator);
    }

    private <T> void register(Class<T> type, ScopeEnum scope, BeanCreator<T> creator) {
        if (definitions.containsKey(type))
            throw new IocException("Bean already registered for type '%s'".formatted(type.getName()));
        definitions.put(type, new BeanDefinition<>(type, scope, creator));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        Optional<BeanDefinition<?>> rawBeanDefinition = definitions.get(type);
        if (rawBeanDefinition.isEmpty())
            throw new IocException("No bean registered for type '%s'".formatted(type.getName()));
        BeanDefinition<T> beanDefinition = (BeanDefinition<T>) rawBeanDefinition.get();
        if (beanDefinition.scope() == ScopeEnum.SINGLETON)
            return getOrCreateSingleton(beanDefinition);
        return createInstance(beanDefinition);
    }

    @SuppressWarnings("unchecked")
    private <T> T getOrCreateSingleton(BeanDefinition<T> beanDefinition) {
        Class<T> type = beanDefinition.type();
        Optional<Object> cachedBeanObject = singletonCache.get(type);
        if (cachedBeanObject.isPresent())
            return (T) cachedBeanObject.get();
        T instance = createInstance(beanDefinition);
        singletonCache.put(type, instance);
        return instance;
    }

    private <T> T createInstance(BeanDefinition<T> def) {
        return def.creator().create(this);
    }
}
