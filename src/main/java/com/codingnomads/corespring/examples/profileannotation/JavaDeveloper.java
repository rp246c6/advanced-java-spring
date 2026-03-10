package com.codingnomads.corespring.examples.profileannotation;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("deploy")
@ToString
public class JavaDeveloper {

    @Value("${app.message}")
    private String message;

    public void printMessage() {
        System.out.println("DeployService message: " + message);
    }

    public JavaDeveloper() {
        System.out.println("JavaDeveloper is ready.");
    }
}

