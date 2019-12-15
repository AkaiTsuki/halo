package io.akaitsuki.halo.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface ExtensionPoint {
    String bizId() default "";
    String useCase() default "";
    String scenario() default "";
}
