package com.codingnomads.springweb.resttemplate.POST.models;

import lombok.Data;

@Data
public class UserResponse {

    private User data;
    private Error error;
    private String status;

}
