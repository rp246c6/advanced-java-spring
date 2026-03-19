package com.codingnomads.springdata.example.ddl.manytomany.unidirectional;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Share {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String sharedTo;
}
