package io.akaitsuki.halo.app.extension.customer;

import io.akaitsuki.halo.core.ExtensionPoint;

@ExtensionPoint(bizId = "biz1", useCase = "getCustomerName", scenario = "vip")
public class VipCustomerNameExtImpl implements CustomerNameExt {
    @Override
    public String customerName(String firstName, String lastName) {
        return "Dear " + firstName + " " + lastName;
    }
}
