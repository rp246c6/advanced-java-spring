package com.codingnomads.springdata.example.ddl.manytoone.unidirectional.usingonetomany;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity // Marks this class as a JPA entity, mapped to a database table
@Getter // Lombok generates getters for all fields
@Setter // Lombok generates setters for all fields
@NoArgsConstructor // Lombok generates a no-argument constructor
public class Category {

    @Id // Primary key of the table
    @GeneratedValue // Auto-generates the ID value (strategy defaults to AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    // 'name' must always be provided, and once set it cannot be updated
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    // Defines a one-to-many relationship with Post.
    // Cascade ensures that operations on Category (persist/remove) propagate to Posts.
    // LAZY fetch means posts are loaded only when explicitly accessed.
    // @JoinColumn creates a foreign key 'category_id' in the posts table.
    private List<Post> posts;
}