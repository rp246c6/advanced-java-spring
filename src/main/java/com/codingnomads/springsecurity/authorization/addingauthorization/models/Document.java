package com.codingnomads.springsecurity.authorization.addingauthorization.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String ownerUsername; // Crucial for ownership-based security

    // Standard Getters/Setters
    public String getOwnerUsername() { return ownerUsername; }
    public String getContent() { return content; }
    public Long getId() { return id; }
}
