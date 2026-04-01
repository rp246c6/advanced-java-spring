/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.DELETE;

import com.codingnomads.springweb.resttemplate.DELETE.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.DELETE.models.Task;
import com.codingnomads.springweb.resttemplate.DELETE.models.User;
import com.codingnomads.springweb.resttemplate.DELETE.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DeleteMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeleteMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("should be deleted")
                    .description("used in a delete RestTemplate example. If you see this something went wrong. Oops")
                    // be sure to enter a valid user id
                    .userId(19481)
                    .completed(false)
                    .build();

            // POST new task to server
            ResponseObject responseObject = restTemplate.postForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask, ResponseObject.class);

            // confirm data was returned & avoid NullPointerExceptions
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The server encountered this error while creating the task:"
                        + responseObject.getError().getMessage());
            } else {
                newTask = responseObject.getData();
            }

            System.out.println("The task was successfully created");
            System.out.println(newTask);

            // delete the newTask using the ID the server returned
            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId());
            System.out.println("The task was also successfully deleted");

            // try to GET, verify record was deleted
            try {
                restTemplate.getForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(), ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            // delete using exchange()
            HttpEntity<Task> httpEntity = new HttpEntity<>(newTask);
            try {
                restTemplate.exchange(
                        "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(),
                        HttpMethod.DELETE,
                        httpEntity,
                        ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            String userUrl = "http://demo.codingnomads.co:8080/tasks_api/users/";

            // --- USER 1: Using delete() ---
            User user1 = User.builder()
                    .email("delete_me_1@example.com")
                    .firstName("Delete")
                    .lastName("Tester One")
                    .build();

            // Create User 1
            UserResponse response1 = restTemplate.postForObject(userUrl, user1, UserResponse.class);
            user1 = response1.getData();
            System.out.println("Created User 1 with ID: " + user1.getId());

            // Delete User 1 using delete()
            restTemplate.delete(userUrl + user1.getId());
            System.out.println("Deleted User 1 using delete().");

            // Verify User 1 is gone
            try {
                restTemplate.getForEntity(userUrl + user1.getId(), UserResponse.class);
            } catch (HttpClientErrorException e) {
                System.out.println("Verification 1: " + e.getMessage()); // Should be 404
            }

            // --- USER 2: Using exchange() ---
            User user2 = User.builder()
                    .email("delete_me_2@example.com")
                    .firstName("Delete")
                    .lastName("Tester Two")
                    .build();

            // Create User 2
            UserResponse response2 = restTemplate.postForObject(userUrl, user2, UserResponse.class);
            user2 = response2.getData();
            System.out.println("\nCreated User 2 with ID: " + user2.getId());

            // delete using exchange()
            HttpEntity<User> userHttpEntity = new HttpEntity<>(user2);
            try {
                ResponseEntity<String> response =  restTemplate.exchange(
                        "http://demo.codingnomads.co:8080/tasks_api/users/" + user2.getId(),
                        HttpMethod.DELETE,
                        userHttpEntity,
                        String.class);
                System.out.println("Response: " + response.getBody());
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

        };
    }
}
