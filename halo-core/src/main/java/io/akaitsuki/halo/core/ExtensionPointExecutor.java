package io.akaitsuki.halo.core;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;

@Component
public class ExtensionPointExecutor {

    @Resource
    private ExtensionRepository extensionRepository;

    @SuppressWarnings("unchecked")
    public <T, R> R execute(Class<T> clazz, BizScenario bizScenario, Function<T, R> function) {
        BizCoordinator bizCoordinator = new BizCoordinator();
        bizCoordinator.setBizId(bizScenario.getBizId());
        bizCoordinator.setUseCase(bizScenario.getUseCase());
        bizCoordinator.setScenario(bizScenario.getScenario());
        bizCoordinator.setClazz(clazz.getName());

        T service = (T)extensionRepository.getServiceMap().get(bizCoordinator);
        return function.apply(service);
    }
}
