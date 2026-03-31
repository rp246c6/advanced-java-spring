/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.POST.postForEntity;

import com.codingnomads.springweb.resttemplate.POST.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.POST.models.Task;
import java.util.Objects;

import com.codingnomads.springweb.resttemplate.POST.models.User;
import com.codingnomads.springweb.resttemplate.POST.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostForEntityMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForEntityMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForEntity()")
                    .description("get comfortable using the RestTemplate postForEntity() method")
                    // be sure to use valid user id
                    .userId(19435)
                    .completed(false)
                    .build();

            User newUser = User.builder()
                    .email("shane.warn@example.com")
                    .firstName("shane")
                    .lastName("warn")
                    .build();


            ResponseEntity<ResponseObject> responseEntity = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            ResponseEntity<UserResponse> userResponseEntity = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/users", newUser, UserResponse.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(responseEntity.getBody()));
            } else {
                System.out.println(
                        Objects.requireNonNull(responseEntity.getBody()).getError());
            }

            if (userResponseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(userResponseEntity.getBody()));
            } else {
                System.out.println(
                        Objects.requireNonNull(userResponseEntity.getBody()).getError());
            }
        };
    }
}
