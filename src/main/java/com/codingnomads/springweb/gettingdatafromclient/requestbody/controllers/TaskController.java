/* CodingNomads (C)2024 */
package com.codingnomads.springweb.gettingdatafromclient.requestbody.controllers;

import com.codingnomads.springweb.gettingdatafromclient.requestbody.models.Task;
import com.codingnomads.springweb.gettingdatafromclient.requestbody.repositories.TaskRepository;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody(required = true) Task task) throws URISyntaxException {
        if (StringUtils.isEmpty(task.getName()) || task.getCompleted() == null) {
            task.setCreatedAt(null);
            return ResponseEntity.badRequest().body(task);
        }
        final Task savedTask = taskRepository.save(Task.builder()
                .completed(task.getCompleted())
                .name(task.getName())
                .build());

        return ResponseEntity.created(new URI("/api/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @PostMapping(value = "/print")
    public ResponseEntity<?> printMessage(@RequestBody(required = false) String message) {

        if (message == null) {
            message = "You did not pass in a message.";
        }
        System.out.println(message);

        if (message.equalsIgnoreCase("I'm a teapot")) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(message);
        } else {
            return ResponseEntity.ok().body(message);
        }
    }

    //bulk create task
    @PostMapping(value = "/api/tasks/bulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Task>> createTasksBulk(@RequestBody java.util.List<Task> tasks) {
        // Saves the entire list of tasks sent in the request body
        Iterable<Task> savedTasks = taskRepository.saveAll(tasks);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTasks);
    }

    //update task
    @PostMapping(value = "/api/tasks/update-completion")
    public ResponseEntity<String> updateStatus(@RequestBody Boolean isCompleted) {
        // This expects a raw boolean value (true/false) in the request body
        String responseMessage = "Global task status set to: " + isCompleted;
        System.out.println(responseMessage);

        return ResponseEntity.ok(responseMessage);
    }
}
