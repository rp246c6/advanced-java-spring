package com.codingnomads.corespring.examples.springbeanlifecycle;

import lombok.NonNull;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class CloudBean implements BeanNameAware {

    @Override
    public void setBeanName(@NonNull String name) {
        System.out.println("Bean name provided, bean name set to: ".concat(name));
    }
}
