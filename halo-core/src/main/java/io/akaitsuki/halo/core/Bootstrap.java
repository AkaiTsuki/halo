package io.akaitsuki.halo.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RegisterFactory registerFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Set<Class<?>> classSet = loadBeans();
        for (Class<?> targetClz : classSet) {
            Register register = registerFactory.getRegister(targetClz);
            if (register != null) {
                register.register(targetClz);
            }
        }
    }

    private Set<Class<?>> loadBeans() {
        Set<Class<?>> classSet = new HashSet<>();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ExtensionPoint.class);
        if (CollectionUtils.isEmpty(map)) {
            return classSet;
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object bean = entry.getValue();
            classSet.add(bean.getClass());
        }

        return classSet;
    }


}
