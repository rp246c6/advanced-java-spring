/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.application_context.customeventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCompletedEventListenerTwo implements ApplicationListener<UserRegistrationCompletedEvent> {

    @Override
    public void onApplicationEvent(UserRegistrationCompletedEvent event) {
        System.out.println("-----Received UserRegistrationCompletedEvent-----");
        System.out.println("-----I'm a second listener!-----");
    }
}
