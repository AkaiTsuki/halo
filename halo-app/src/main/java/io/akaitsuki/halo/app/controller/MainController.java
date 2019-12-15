package io.akaitsuki.halo.app.controller;

import io.akaitsuki.halo.app.extension.customer.CustomerNameExt;
import io.akaitsuki.halo.app.extension.customer.UserCustomerNameExtImpl;
import io.akaitsuki.halo.core.BizCoordinator;
import io.akaitsuki.halo.core.ExtensionPointExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainController {

    @Autowired
    private ExtensionPointExecutor extensionPointExecutor;

    @GetMapping("/check.htm")
    public String check() {
        return "success";
    }


    @GetMapping("/cutomerName/{userType}")
    public String getCustomerName(@PathVariable("userType") String userType) {

        BizCoordinator bizCoordinator = new BizCoordinator();
        bizCoordinator.setBizId("biz1");
        bizCoordinator.setUseCase("getCustomerName");
        bizCoordinator.setScenario(userType);

        return extensionPointExecutor.execute(CustomerNameExt.class, bizCoordinator, extension -> extension.customerName("John", "Clark"));
    }
}
