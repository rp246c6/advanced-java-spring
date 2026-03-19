package com.codingnomads.corespring.examples.application_context.frameworkevents;

import lombok.NonNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// @Component tells Spring that this is a bean it should register
@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("ApplicationReadyEvent has fired!!");
    }
}
