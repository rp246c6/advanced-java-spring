package com.codingnomads.corespring.examples.dependsonannotation;

import org.springframework.beans.factory.annotation.Autowired;

public class JavaDeveloper {

    @Autowired
    private JDK jdk;

    public JavaDeveloper() {
        System.out.println("JavaDeveloper is ready.");
    }
}
