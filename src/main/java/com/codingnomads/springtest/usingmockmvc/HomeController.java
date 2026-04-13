/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingmockmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bobbert");
        return "greeting";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String greet() {
        return "Hello Back";
    }

    // 1. Simple Path Variable
    @GetMapping("/echo/{msg}")
    @ResponseBody
    public String echo(@PathVariable String msg) {
        return "Echo: " + msg;
    }

    // 2. JSON Response (using a Map for simplicity)
    @GetMapping("/api/status")
    @ResponseBody
    public Map<String, String> getStatus() {
        return Collections.singletonMap("status", "UP");
    }

    // 3. Query Parameter with Model
    @GetMapping("/custom-greeting")
    public String customGreeting(@RequestParam(name="user") String user, Model model) {
        model.addAttribute("name", user);
        return "greeting";
    }

}
