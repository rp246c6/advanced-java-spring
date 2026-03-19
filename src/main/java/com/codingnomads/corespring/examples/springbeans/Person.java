package com.codingnomads.corespring.examples.springbeans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private String name;
    private Integer age;

    public Person(String name,Integer age) {
        this.age = age;
        this.name = name;
    }
}
