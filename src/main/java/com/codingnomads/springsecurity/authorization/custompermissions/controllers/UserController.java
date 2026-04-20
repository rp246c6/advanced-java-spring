/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.custompermissions.controllers;

import com.codingnomads.springsecurity.authorization.custompermissions.models.User;
import com.codingnomads.springsecurity.authorization.custompermissions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "permissions";
    }

    @GetMapping("/user")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public User getEntityById(@RequestParam String email) {
        return userService.getUser(email);
    }

    @GetMapping("/user/delete/{id}")
    @ResponseBody
    @PreAuthorize(
            "hasPermission(#id, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'DELETE')")
    public String deleteEntity(@PathVariable Long id) {
        userService.deleteUser(id);
        return ("deleted user with id: " + id);
    }

    /**
     * Updates a user's details.
     * @PreAuthorize ensures the authenticated user has 'UPDATE' permission for the specific user ID
     * before the method executes.
     */
    @GetMapping("/user/update/{id}") // Changed from @PutMapping
    @ResponseBody
    @PreAuthorize("hasPermission(#id, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'UPDATE')")
    public String updateEntity(@PathVariable Long id) {
        // In a real app, you'd pass a User object, but for testing links, this works!
        return "SUCCESS: You have permission to update user with id: " + id;
    }

    /**
     * Retrieves a summary of a user.
     * @PostAuthorize allows the method to execute but checks if the user has 'READ' permission
     * on the returned User object before sending the response.
     */
    @GetMapping("/user/summary")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public User getUserSummary(@RequestParam String email) {
        return userService.getUser(email);
    }

}
