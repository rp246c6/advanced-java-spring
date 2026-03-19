/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.primaryannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrimaryAnnotationDemo {

    @Autowired
    DesktopComputer desktopComputer;

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(PrimaryAnnotationDemo.class);
        ctx.close();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            System.out.println(desktopComputer);

        };
    }
}
