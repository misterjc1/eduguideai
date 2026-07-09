package com.wouti.zoom_academia.services;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface AiAgent {
    @AliasFor(annotation = Component.class)
    public String value() default "";
}

