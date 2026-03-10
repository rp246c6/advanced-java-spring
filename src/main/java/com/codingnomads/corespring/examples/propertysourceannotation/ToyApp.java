/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.propertysourceannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ToyApp {
    @Value("${toyapp.name}")
    private String appName;

    @Value("${toyapp.version}")
    private String appVersion;

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }
}
