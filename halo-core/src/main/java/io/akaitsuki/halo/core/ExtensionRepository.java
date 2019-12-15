package io.akaitsuki.halo.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExtensionRepository {

    private Map<BizCoordinator, IExtensionPoint> serviceMap = new HashMap<>();

    public Object put(BizCoordinator bizCoordinator, IExtensionPoint service) {
        return serviceMap.put(bizCoordinator, service);
    }

    public IExtensionPoint get(BizCoordinator bizCoordinator) {
        return serviceMap.get(bizCoordinator);
    }

    public Map<BizCoordinator, IExtensionPoint> getServiceMap() {
        return serviceMap;
    }

    public void setServiceMap(Map<BizCoordinator, IExtensionPoint> serviceMap) {
        this.serviceMap = serviceMap;
    }
}
