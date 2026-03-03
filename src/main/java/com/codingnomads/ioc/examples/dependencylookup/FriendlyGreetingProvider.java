package com.codingnomads.ioc.examples.dependencylookup;
/*
FriendlyGreetingProvider class that implements GreetingProvider,
and in its getGreeting() method return  greeting
 */
public class FriendlyGreetingProvider implements GreetingProvider{

    @Override
    public String getGreeting() {
        return "Hello there! Wishing you a great day!";
    }
}
