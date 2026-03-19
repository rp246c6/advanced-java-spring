package com.codingnomads.springdata.example.ddl.manytomany.jointableexample;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    // many to many annotation defers to the locations field in the Post class
    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors;
}
