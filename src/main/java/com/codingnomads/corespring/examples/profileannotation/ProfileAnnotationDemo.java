/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProfileAnnotationDemo {
    public static void main(String[] args) {


        final ConfigurableApplicationContext ctx = SpringApplication.run(ProfileAnnotationDemo.class);

        // Check active profiles
        Environment env = ctx.getEnvironment();
        String[] activeProfiles = env.getActiveProfiles();

        // Fetch beans based on profile
        String profile = activeProfiles[0];

        if (profile.equals("deploy")) {

            final JavaDeveloper javaDeveloper = ctx.getBean(JavaDeveloper.class);
            System.out.println("JavaDeveloper bean is active: " + javaDeveloper);
            javaDeveloper.printMessage();

        } else if (profile.equals("test")) {

            final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
            System.out.println("SpringDeveloper bean is active: " + springDeveloper);
            springDeveloper.printMessage();

        }

        ctx.close();
    }
}
