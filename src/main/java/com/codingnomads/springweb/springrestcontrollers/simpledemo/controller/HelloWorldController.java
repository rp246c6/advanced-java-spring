/* CodingNomads (C)2024 */
package com.codingnomads.springweb.springrestcontrollers.simpledemo.controller;

import com.codingnomads.springweb.springrestcontrollers.simpledemo.models.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Spring Developer!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }

    // 1. Returns a List of Task names (Strings)
    @RequestMapping(path = "/task-names", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTaskNames() {
        return Arrays.asList("Initialize Project", "Build Controller", "Test JSON");
    }

    // 2. Returns a POJO using your Lombok @Builder
    @RequestMapping(path = "/task", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getSingleTask() {
        return Task.builder()
                .id(101)
                .userId(5)
                .name("Learn Spring REST")
                .description("Mastering controllers and POJOs")
                .completed(false)
                .createdAt(System.currentTimeMillis())
                .build();
    }

}
