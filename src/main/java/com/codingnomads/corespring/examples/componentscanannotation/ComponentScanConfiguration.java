/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.componentscanannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan (basePackages = "com.codingnomads.corespring.examples.componentscanannotation.tools")
//@ComponentScan (basePackages = "com.codingnomads.corespring.examples.componentscanannotation.account")
public class ComponentScanConfiguration {

    @Bean
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
