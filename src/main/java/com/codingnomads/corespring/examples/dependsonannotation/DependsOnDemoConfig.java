/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.dependsonannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class DependsOnDemoConfig {

    @Bean
    @DependsOn(value = "jdk")
    public SpringDeveloper springDeveloper() {
        return new SpringDeveloper();
    }

    @Bean
    @DependsOn(value = "jdk")
    public JavaDeveloper javaDeveloper() {
        return new JavaDeveloper();
    }

    @Bean("jdk")
    public JDK jdk() {
        return new JDK();
    }
}
