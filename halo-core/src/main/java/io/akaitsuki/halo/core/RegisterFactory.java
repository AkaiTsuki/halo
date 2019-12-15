package io.akaitsuki.halo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterFactory {
    @Autowired
    private ExtensionRegister extensionRegister;

    public Register getRegister(Class<?> targetClz) {
        ExtensionPoint ep = targetClz.getDeclaredAnnotation(ExtensionPoint.class);
        if (ep != null) {
            return extensionRegister;
        }

        return null;
    }
}
