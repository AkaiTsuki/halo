package io.akaitsuki.halo.app.extension.customer;

import io.akaitsuki.halo.core.ExtensionPoint;

@ExtensionPoint(bizId = "biz1", useCase = "getCustomerName", scenario = "normal")
public class UserCustomerNameExtImpl implements CustomerNameExt {
    @Override
    public String customerName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
