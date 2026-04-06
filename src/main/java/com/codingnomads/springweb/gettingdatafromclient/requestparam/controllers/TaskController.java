/* CodingNomads (C)2024 */
package com.codingnomads.springweb.gettingdatafromclient.requestparam.controllers;

import com.codingnomads.springweb.gettingdatafromclient.requestparam.models.Task;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTask(@RequestParam Long id) {
        return "ID: " + id;
    }

    @GetMapping(value = "/param-name-variable-name-different", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTaskWithDifferentParamAndVariableName(@RequestParam(name = "id") Long taskId) {
        return "ID: " + taskId;
    }

    @GetMapping(value = "/request-param-optional", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTaskWithOptionalRequestPram(@RequestParam(name = "id", required = false) Long taskId) {
        if (taskId != null) {
            return Task.builder().id(taskId).name("Task One").build();
        } else return Task.builder().name("Task One").build();
    }

    @GetMapping(value = "/default-request-param-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTaskWithDefaultRequestParam(
            @RequestParam(name = "name", required = false, defaultValue = "Task One") String taskName) {
        return Task.builder().name(taskName).build();
    }

    @GetMapping(value = "/request-parameter-with-multiple-values", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasksWithNamesRequestParam(@RequestParam(name = "names") List<String> names) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> Task.builder().id((long) i).name(names.get(i)).build())
                .collect(Collectors.toList());
    }

    // 1. Filtering by multiple criteria (Status and Category)
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public String filterTasks(
            @RequestParam String status,
            @RequestParam(required = false, defaultValue = "General") String category) {
        return String.format("Filtering tasks for Status: %s and Category: %s", status, category);
    }

    // 2. Pagination (Common for AWS/Cloud datasets)
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getPagedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        // Mocking a paged response
        return IntStream.range(0, pageSize)
                .mapToObj(i -> Task.builder()
                        .id((long) (page * pageSize + i))
                        .name("Task " + (page * pageSize + i))
                        .build())
                .collect(Collectors.toList());
    }

    // 3. Search with a specific character limit (Validation example)
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchTasks(@RequestParam(name = "q") String query) {
        if (query.length() < 3) {
            return "Search query too short. Please use at least 3 characters.";
        }
        return "Searching for tasks containing: " + query;
    }
}
