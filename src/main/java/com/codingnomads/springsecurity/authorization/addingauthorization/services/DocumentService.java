package com.codingnomads.springsecurity.authorization.addingauthorization.services;

import com.codingnomads.springsecurity.authorization.addingauthorization.models.Document;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    // 1. @PreAuthorize: Check if the user is an ADMIN before even calling the method
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDocument(Long id) {
        System.out.println("Deleting document: " + id);
    }

    // 2. @PostAuthorize: Check ownership AFTER retrieving the object
    // If the return object's owner isn't the logged-in user, access is denied.
    @PostAuthorize("returnObject.ownerUsername == authentication.name")
    public Document getDocumentById(Long id) {
        // Mock data: In a real app, this comes from a database
        return new Document(id, "Secret Content", "user123");
    }

    // 3. @PostFilter: Automatically removes items from the list the user shouldn't see
    @PostFilter("filterObject.ownerUsername == authentication.name or hasRole('ADMIN')")
    public List<Document> getAllDocuments() {
        List<Document> docs = new ArrayList<>();
        docs.add(new Document(1L, "My Diary", "user123"));
        docs.add(new Document(2L, "Someone Else's Work", "other_user"));
        return docs;
    }

    // 4. @PreFilter: Filters a collection PASSED AS AN ARGUMENT
    // Use this to prevent processing documents that are "restricted" or invalid
    @PreFilter("filterObject.content != 'restricted'")
    public void bulkUpdateDocuments(List<Document> documents) {
        documents.forEach(doc -> System.out.println("Updating: " + doc.getContent()));
    }
}
