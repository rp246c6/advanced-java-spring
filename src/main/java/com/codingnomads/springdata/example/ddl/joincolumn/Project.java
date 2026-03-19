package com.codingnomads.springdata.example.ddl.joincolumn;
/*
Each relationship uses a different  value:
• 	 for User ↔ Project
• 	 for Project ↔ Task
• 	 for Task ↔ Example
• 	 +  for Example ↔ User
This ensures your schema is explicit, readable, and avoids collisions in foreign key column names.*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String projectName;

    // Many projects belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")  // custom FK column
    private User owner;

    // One project can have many tasks
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}