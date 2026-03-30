/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.GET.getForEntity;

import com.codingnomads.springweb.resttemplate.GET.models.Activity;
import com.codingnomads.springweb.resttemplate.GET.models.QuoteTemplate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetForEntityDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForEntityDemo.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            ResponseEntity<QuoteTemplate[]> responseEntity =
                    restTemplate.getForEntity("https://zenquotes.io/api/random", QuoteTemplate[].class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
                QuoteTemplate[] quoteTemplate = responseEntity.getBody();
                System.out.println(Arrays.toString(quoteTemplate));
            } else {
                System.out.println("Something went wrong! The response was not marked with status code 200");
            }

            // Base URL with placeholders
            String url = "https://bored-api.appbrewery.com/filter?type={type}&participants={participants}";

            // Create a map of parameters
            Map<String, String> params = new HashMap<>();
            params.put("type", "education");
            params.put("participants", "1");

            // Submit GET request with params
            ResponseEntity<Activity[]> activityResponseEntity = restTemplate.getForEntity(url, Activity[].class,params);


            if (activityResponseEntity.getStatusCode().equals(HttpStatus.OK) && activityResponseEntity.getBody() != null) {
                Activity[] activityTemplate = activityResponseEntity.getBody();
                System.out.println(Arrays.toString(activityTemplate));
            } else {
                System.out.println("Something went wrong! The response was not marked with status code 200");
            }


        };
    }
}
