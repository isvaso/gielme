package com.isvaso.ioc.configuration;

import com.isvaso.encryption.XorEncryptor;
import com.isvaso.ioc.core.BeanContainer;

public class EncryptionConfiguration implements BeanConfiguration {

    @Override
    public void configure(BeanContainer beanContainer) {
        beanContainer.registerPrototype(XorEncryptor.class, bc -> new XorEncryptor());
    }
}
