package com.codingnomads.springdata.example.ddl.joincolumn;
/*
Each relationship uses a different  value:
• 	 for User ↔ Project
• 	 for Project ↔ Task
• 	 for Task ↔ Example
• 	 +  for Example ↔ User
This ensures your schema is explicit, readable, and avoids collisions in foreign key column names.
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private boolean completed;

    // Many tasks belong to one project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_ref", referencedColumnName = "id")  // custom FK column
    private Project project;

    // Each task can be linked to one Example
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "example_ref", referencedColumnName = "id")  // custom FK column
    private Example example;
}