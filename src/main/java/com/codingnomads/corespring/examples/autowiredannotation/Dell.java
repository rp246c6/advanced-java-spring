package com.codingnomads.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("dell")
@ToString
public class Dell implements GraphicCard{
}
