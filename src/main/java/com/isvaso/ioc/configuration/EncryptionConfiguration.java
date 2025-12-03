package com.isvaso.ioc.configuration;

import com.isvaso.encryption.XorEncryptor;
import com.isvaso.ioc.BeanContainer;
import com.isvaso.serialization.DataVersionSerializer;
import com.isvaso.serialization.TaskSerializer;

public class EncryptionConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerPrototype(XorEncryptor.class, bc -> new XorEncryptor());
    }
}
