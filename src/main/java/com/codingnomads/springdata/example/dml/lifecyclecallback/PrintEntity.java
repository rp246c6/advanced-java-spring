/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrintEntity {

    @Id
    @GeneratedValue
    private Long id;

    // write your methods here

    private String message;

    // Called before the entity is persisted (inserted)
    @PrePersist
    public void printBeforePersist() {
        System.out.println("Preparing to persist PrintEntity...");
    }

    // Called after the entity is persisted (inserted)
    @PostPersist
    public void printAfterPersist() {
        System.out.println("PrintEntity has been persisted successfully!");
    }

    // Called after the entity is loaded from the database
    @PostLoad
    public void printAfterLoad() {
        System.out.println("PrintEntity has been loaded from the database.");
    }

    // Called before the entity is updated
    @PreUpdate
    public void printBeforeUpdate() {
        System.out.println("Preparing to update PrintEntity...");
    }

    // Called after the entity is updated
    @PostUpdate
    public void printAfterUpdate() {
        System.out.println("PrintEntity has been updated successfully!");
    }

    // Called before the entity is removed (deleted)
    @PreRemove
    public void printBeforeRemove() {
        System.out.println("Preparing to remove PrintEntity...");
    }

    // Called after the entity is removed (deleted)
    @PostRemove
    public void printAfterRemove() {
        System.out.println("PrintEntity has been removed successfully!");
    }






}
