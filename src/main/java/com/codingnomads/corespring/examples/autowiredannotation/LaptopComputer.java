package com.codingnomads.corespring.examples.autowiredannotation;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@ToString
public class LaptopComputer {

    @Autowired
    @Qualifier("radeon") private VideoCard videoCard;

    @Autowired
    @Qualifier("dell") private GraphicCard graphicCard;
}
