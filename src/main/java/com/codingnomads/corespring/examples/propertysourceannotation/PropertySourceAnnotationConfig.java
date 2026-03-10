/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.propertysourceannotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource(value = {"classpath:myapp.properties","classpath:toyapp.properties"}, ignoreResourceNotFound = true)
public class PropertySourceAnnotationConfig {}
