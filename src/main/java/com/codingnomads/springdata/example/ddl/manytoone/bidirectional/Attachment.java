package com.codingnomads.springdata.example.ddl.manytoone.bidirectional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Post post;

}
