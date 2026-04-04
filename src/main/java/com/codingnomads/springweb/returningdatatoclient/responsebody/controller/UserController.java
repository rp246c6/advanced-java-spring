/* CodingNomads (C)2024 */
package com.codingnomads.springweb.returningdatatoclient.responsebody.controller;

import com.codingnomads.springweb.returningdatatoclient.responsebody.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    public User user = User.builder()
            .id(1000)
            .name("Spring Dev")
            .email("dev@codingnomads.com")
            .build();

    // using ResponseBody to return a POJO
    @ResponseBody
    @GetMapping("/response-body")
    public User userResponseBody() {
        return user;
    }

    // using ResponseEntity to return POJO
    @GetMapping("/response-entity")
    public ResponseEntity<User> userResponseEntity() {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // returning a POJO without ResponseBody or using a ResponseEntity - error expected
    @GetMapping("/user")
    public User user() {
        return user;
    }

    public User user2 = User.builder()
            .id(1001)
            .name("Java Expert")
            .email("expert@codingnomads.com")
            .build();

    public User user3 = User.builder()
            .id(1002)
            .name("Cloud Arch")
            .email("arch@codingnomads.com")
            .build();

    public User user4 = User.builder()
            .id(1003)
            .name("Data Scientist")
            .email("data@codingnomads.com")
            .build();

    // Adding all objects to a List
    public List<User> userList = Arrays.asList(user, user2, user3, user4);

    // Endpoint returning the list of users as JSON
    @ResponseBody
    @GetMapping("/user-list")
    public List<User> getAllUsers() {
        return userList;
    }

}

