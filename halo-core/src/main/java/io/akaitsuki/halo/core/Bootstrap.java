package io.akaitsuki.halo.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.Map;


public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private ExtensionRepository extensionRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ExtensionPoint.class);
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object instance = entry.getValue();
                ExtensionPoint ep = instance.getClass().getAnnotation(ExtensionPoint.class);
                BizCoordinator bizCoordinator = new BizCoordinator();
                bizCoordinator.setBizId(ep.bizId());
                bizCoordinator.setUseCase(ep.useCase());
                bizCoordinator.setScenario(ep.scenario());
                bizCoordinator.setClazz(calculateExtensionPoint(instance.getClass()));

                if (extensionRepository.get(bizCoordinator) != null) {
                    throw new RuntimeException("Find duplicate class with same value");
                }

                extensionRepository.put(bizCoordinator, instance);
            }
        }
    }

    private String calculateExtensionPoint(Class<?> targetClz) {
        Class[] interfaces = targetClz.getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            throw new RuntimeException("Please assign a extension point interface for " + targetClz);
        }

        for (Class intf : interfaces) {
            String extensionPoint = intf.getSimpleName();
            if (extensionPoint.endsWith("Ext"))
                return intf.getName();
        }

        throw new RuntimeException("Your name of ExtensionPoint for "+targetClz+" is not valid, must be end of "+"Ext");
    }
}
