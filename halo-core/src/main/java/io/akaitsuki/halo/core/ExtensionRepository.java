package io.akaitsuki.halo.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExtensionRepository {

    private Map<BizCoordinator, Object> serviceMap = new HashMap<>();

    public Object put(BizCoordinator bizCoordinator, Object service) {
        return serviceMap.put(bizCoordinator, service);
    }

    public Object get(BizCoordinator bizCoordinator) {
        return serviceMap.get(bizCoordinator);
    }

    public Map<BizCoordinator, Object> getServiceMap() {
        return serviceMap;
    }

    public void setServiceMap(Map<BizCoordinator, Object> serviceMap) {
        this.serviceMap = serviceMap;
    }
}
