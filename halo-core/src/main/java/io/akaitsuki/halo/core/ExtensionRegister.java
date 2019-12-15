package io.akaitsuki.halo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtensionRegister implements Register {

    public static final String EXTENSION_POINT_SUFFIX = "Ext";

    @Autowired
    private ExtensionRepository extensionRepository;

    @Override
    public void register(Class<?> targetClz) {
        IExtensionPoint extension = (IExtensionPoint) ApplicationContextHelper.getBean(targetClz);
        ExtensionPoint ep = extension.getClass().getDeclaredAnnotation(ExtensionPoint.class);
        String clzName = calculateExtensionPoint(targetClz);
        BizCoordinator bizCoordinator = new BizCoordinator();
        bizCoordinator.setBizId(ep.bizId());
        bizCoordinator.setUseCase(ep.useCase());
        bizCoordinator.setScenario(ep.scenario());
        bizCoordinator.setClazz(clzName);

        if (extensionRepository.get(bizCoordinator) != null) {
            throw new RuntimeException("Find duplicate class with same value for " + bizCoordinator);
        }

        extensionRepository.put(bizCoordinator, extension);
    }

    private String calculateExtensionPoint(Class<?> targetClz) {
        Class[] interfaces = targetClz.getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            throw new RuntimeException("Please assign a extension point interface for " + targetClz);
        }

        for (Class intf : interfaces) {
            String extensionPoint = intf.getSimpleName();
            if (extensionPoint.endsWith(EXTENSION_POINT_SUFFIX))
                return intf.getName();
        }

        throw new RuntimeException("Your name of ExtensionPoint for " + targetClz + " is not valid, must be end of " + EXTENSION_POINT_SUFFIX);
    }
}
