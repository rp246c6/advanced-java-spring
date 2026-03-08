package com.codingnomads.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("hp")
@ToString
public class Hp implements GraphicCard{
}
