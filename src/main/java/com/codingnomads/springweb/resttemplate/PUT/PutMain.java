/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.PUT;

import com.codingnomads.springweb.resttemplate.PUT.models.User;
import com.codingnomads.springweb.resttemplate.PUT.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.PUT.models.Task;
import com.codingnomads.springweb.resttemplate.PUT.models.UserResponse;
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
public class PutMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PutMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            // use a valid task id
            int taskId = 18304;

            // use a valid user id
            int userId = 19460;

            // request Task 5 from server
            ResponseObject responseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);

            // request userId  from server
            UserResponse userResponseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userId, UserResponse.class);

            // confirm data was retrieved & avoid NullPointerExceptions
            Task taskToUpdate;
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The task with ID " + taskId + " could not be found");
            } else {
                taskToUpdate = responseObject.getData();
            }

            // confirm data was retrieved & avoid NullPointerExceptions
            User userToUpdate;
            if (userResponseObject == null) {
                throw new Exception("The server did not return anything. Not even a userResponseObject!");
            } else if (userResponseObject.getData() == null) {
                throw new Exception("The user with ID " + userId + " could not be found");
            } else {
                userToUpdate = userResponseObject.getData();
            }

            // update the task information
            taskToUpdate.setName("updated using put() - video demo ");
            taskToUpdate.setDescription("this task was updated using RestTemplate's put() method - video demo");
            taskToUpdate.setCompleted(false);

            // update the user information
            userToUpdate.setFirstName("Maria");
            userToUpdate.setLastName("G");
            userToUpdate.setEmail("Maria.Garcia@example.com");

            // use put to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(), taskToUpdate);
            // get the task to verify update
            responseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);
            System.out.println(responseObject.toString());

            taskToUpdate.setName("updated using exchange() PUT - video demo 2");
            taskToUpdate.setDescription("this task was updated using RestTemplate's exchange() method - video demo 2");

            // use put to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate.getId(), userToUpdate);
            // get the user to verify update
            userResponseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userId, UserResponse.class);
            System.out.println(userResponseObject.toString());

            userToUpdate.setFirstName("Maria");
            userToUpdate.setLastName("Gracia");
            userToUpdate.setEmail("Maria.Gracia@example.com");

            // create an HttpEntity wrapping the task to update
            HttpEntity<Task> httpEntity = new HttpEntity<>(taskToUpdate);
            // use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<ResponseObject> response = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(),
                    HttpMethod.PUT,
                    httpEntity,
                    ResponseObject.class);
            System.out.println(response.toString());

            // create an HttpEntity wrapping the user to update
            HttpEntity<User> userhttpEntity = new HttpEntity<>(userToUpdate);
            // use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<UserResponse> userResponse = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate.getId(),
                    HttpMethod.PUT,
                    userhttpEntity,
                    UserResponse.class);
            System.out.println(userResponse.toString());
        };
    }
}
