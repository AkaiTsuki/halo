package io.akaitsuki.halo.app.extension.customer;

import io.akaitsuki.halo.core.IExtensionPoint;

public interface CustomerNameExt extends IExtensionPoint {
    String customerName(String firstName, String lastName);
}
