/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class SpringDeveloper {

    @Value("${app.message}")
    private String message;

    public void printMessage() {
        System.out.println("TestService message: " + message);
    }

    public SpringDeveloper() {
        System.out.println("SpringDeveloper is ready.");
    }
}
