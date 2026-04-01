/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.PATCH;

import com.codingnomads.springweb.resttemplate.PATCH.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.PATCH.models.Task;
import java.util.Objects;

import com.codingnomads.springweb.resttemplate.PATCH.models.User;
import com.codingnomads.springweb.resttemplate.PATCH.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PatchMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PatchMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            // create an empty Task
            Task task = new Task();

            // be sure to use a valid task id
            task.setId(18325);

            // set fields you want to change. All other fields are null and will not be updated
            task.setName("use patchForObject()");
            task.setDescription("this task was updated using patchForObject()");

            // send the PATCH request using the URL, the Task created above and the ResponseObject Class
            ResponseObject objectResponse = restTemplate.patchForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), task, ResponseObject.class);

            System.out.println(Objects.requireNonNull(objectResponse));

            task.setName("PATCH using exchange()");
            task.setDescription("This task was updated using PATCH");

            HttpEntity<Task> httpEntity = new HttpEntity<>(task);
            ResponseEntity<ResponseObject> response = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(),
                    HttpMethod.PATCH,
                    httpEntity,
                    ResponseObject.class);

            System.out.println(Objects.requireNonNull(response));

            String baseUrl = "http://demo.codingnomads.co:8080/tasks_api/users/";

            // 1. PATCH User 1 using patchForObject()
            User user1 = new User();
            user1.setId(19482); // Replace with a valid User ID from the Demo API
            user1.setFirstName("Updated_Via_PatchForObject");

            UserResponse user1Response = restTemplate.patchForObject(
                    baseUrl + user1.getId(),
                    user1,
                    UserResponse.class);

            System.out.println("User 1 Response: " + Objects.requireNonNull(user1Response));

            // 2. PATCH User 2 using exchange()
            User user2 = new User();
            user2.setId(19483); // Replace with another valid User ID
            user2.setLastName("Updated_Via_Exchange");

            HttpEntity<User> user2Entity = new HttpEntity<>(user2);
            ResponseEntity<UserResponse> user2Response = restTemplate.exchange(
                    baseUrl + user2.getId(),
                    HttpMethod.PATCH,
                    user2Entity,
                    UserResponse.class);

            System.out.println("User 2 Response Status: " + user2Response.getStatusCode());
            System.out.println("User 2 Response Body: " + Objects.requireNonNull(user2Response.getBody()));


        };
    }
}
