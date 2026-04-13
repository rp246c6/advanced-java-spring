/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingmockmvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// tell Spring to start completely and indicate the location of the bootstrapping class
@SpringBootTest(classes = MockMvcMain.class)
// indicate that Spring should autoconfigure the MockMvc object
@AutoConfigureMockMvc
public class TestWebServices {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloShouldReturnDefaultMessage() throws Exception {
        // use mockMvc to start a request
        mockMvc
                // .perform is used to indicate what mockMvc should do
                .perform(
                        // the get method and the path passed in as a parameter is used to indicate the
                        // HTTP method and the url path used to make request
                        get("/hello"))
                // print the response
                .andDo(print())
                // the response should have status 200 OK
                .andExpect(status().isOk())
                // test that this response has a body that contains a "Hello Back" String
                .andExpect(content().string(containsString("Hello Back")));
    }

    @Test
    public void baseURLShouldReturnGreetingViewName() throws Exception {
        // use mockMvc to start a request
        mockMvc
                // indicate what HTTP method and url path should be used to make the request
                .perform(get("/"))
                // the result should be printed
                .andDo(print())
                // the view name expected is greeting
                .andExpect(view().name("greeting"));
    }

    // Technique 1: Path Variable & Plain Text Content
    @Test
    public void testEcho() throws Exception {
        mockMvc.perform(get("/echo/HelloAWS"))
                .andExpect(status().isOk())
                .andExpect(content().string("Echo: HelloAWS"));
    }

    // Technique 2: JSON Response & JsonPath Validation
    @Test
    public void testGetStatusJson() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    // Technique 3: Query Params & View/Model Verification
    @Test
    public void testCustomGreeting() throws Exception {
        mockMvc.perform(get("/custom-greeting").param("user", "CloudEngineer"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("name", "CloudEngineer"))
                .andExpect(content().string(containsString("CloudEngineer")));
    }
}
