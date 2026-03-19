/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LifecycleCallbackDemo {

    public static void main(String[] args) {
        SpringApplication.run(LifecycleCallbackDemo.class);
    }

    @Autowired
    PrintEntityRepository  printEntityRepository;

    @Bean
    public CommandLineRunner runStuff(PrintEntityRepository printEntityRepository) {
        return (args) -> {
            // put your logic here

            // First entity
            PrintEntity entity1 = new PrintEntity();
            entity1.setMessage("Hello from entity 1!");
            printEntityRepository.save(entity1);

            // Second entity
            PrintEntity entity2 = new PrintEntity();
            entity2.setMessage("Hello from entity 2!");
            printEntityRepository.save(entity2);

            System.out.println("Two PrintEntity instances have been persisted.");

        };
    }
}
