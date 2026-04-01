package com.codingnomads.springweb.resttemplate.DELETE.models;

import com.codingnomads.springweb.resttemplate.POST.models.Error;
import lombok.Data;

@Data
public class UserResponse {

    private User data;
    private Error error;
    private String status;

}
