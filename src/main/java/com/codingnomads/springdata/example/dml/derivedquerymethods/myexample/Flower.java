package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flowers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Flower {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;   // e.g., "Rose", "Tulip"

    private String color;  // e.g., "Red", "Yellow"

    private String season; // e.g., "Spring", "Summer"

    private String sunType;

    private Integer numDaysTillDayOfBloom;

    @Column(nullable = false)
    private boolean fragranceBearing;
}
