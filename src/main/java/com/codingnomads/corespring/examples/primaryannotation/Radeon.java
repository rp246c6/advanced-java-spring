/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.primaryannotation;

import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ToString
public class Radeon implements VideoCard {}
