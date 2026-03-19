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
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "doctor_patient", // join table name
            joinColumns = @JoinColumn(name = "doctor_id"), // FK to Doctor
            inverseJoinColumns = @JoinColumn(name = "patient_id") // FK to Patient
    )
    private Set<Patient> patients;


}
