/* CodingNomads (C)2024 */
package com.codingnomads.springweb.returningdatatoclient.usingresponseentity.controller;

import com.codingnomads.springweb.returningdatatoclient.usingresponseentity.model.User;
import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    User user = new User(1, "Test User", "test@email.com");

    @GetMapping("/constructor")
    public ResponseEntity<User> constructorMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("TEST", "TEST HEADER");
        headers.add("Location", "/users/" + user.getId());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @GetMapping("/builder")
    public ResponseEntity<User> builderMethod() {
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .header("TEST", "TEST HEADER")
                .body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (user.getId() == id) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    // --- NEW PRACTICE ENDPOINT ---
    @GetMapping("/practice")
    public ResponseEntity<?> practice(@RequestParam(required = false) String action) {

        // 1. Manipulate Body (Returning a Map/JSON)
        if ("data".equals(action)) {
            return ResponseEntity.ok(Map.of("status", "success", "info", "Practice data retrieved"));
        }

        // 2. Manipulate Status Code (418 I'm a teapot)
        if ("teapot".equals(action)) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Short and stout!");
        }

        // 3. Manipulate Headers (Custom metadata)
        if ("header".equals(action)) {
            return ResponseEntity.ok()
                    .header("X-Practice-Custom", "Spring-Magic")
                    .body("Check your response headers!");
        }

        // Default: 200 OK with String body
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to the Practice Endpoint");
    }

}
