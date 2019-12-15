package io.akaitsuki.halo.app.controller;

import io.akaitsuki.halo.app.extension.customer.CustomerNameExt;
import io.akaitsuki.halo.core.BizScenario;
import io.akaitsuki.halo.core.ExtensionPointExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

        BizScenario bizScenario = new BizScenario();
        bizScenario.setBizId("biz1");
        bizScenario.setUseCase("getCustomerName");
        bizScenario.setScenario(userType);

        return extensionPointExecutor.execute(CustomerNameExt.class, bizScenario, extension -> extension.customerName("John", "Clark"));
    }
}
