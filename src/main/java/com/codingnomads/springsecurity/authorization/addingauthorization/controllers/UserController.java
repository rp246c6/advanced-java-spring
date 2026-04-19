/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.addingauthorization.controllers;

import com.codingnomads.springsecurity.authorization.addingauthorization.models.Document;
import com.codingnomads.springsecurity.authorization.addingauthorization.models.UserMeta;
import com.codingnomads.springsecurity.authorization.addingauthorization.models.UserPrincipal;
import com.codingnomads.springsecurity.authorization.addingauthorization.services.CustomUserService;
import com.codingnomads.springsecurity.authorization.addingauthorization.services.DocumentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    CustomUserService userDetailsService;


    @Autowired
    private DocumentService documentService;

    @GetMapping("/documents")
    public List<Document> getDocs() {
        // If logged in as 'user123', @PostFilter in the service
        // will remove the document belonging to 'other_user'.
        return documentService.getAllDocuments();
    }

    @GetMapping("/document/{id}")
    public Document getOneDoc(@PathVariable Long id) {
        // @PostAuthorize will throw an AccessDeniedException
        // if you try to access a doc you don't own.
        return documentService.getDocumentById(id);
    }

    @PostMapping("/update-user")
    @PreAuthorize("hasRole('ADMIN') or #userToUpdate.username == authentication.name")
    public UserMeta updateUser(@RequestBody UserMeta userToUpdate) {
        return userDetailsService.updateUserMeta(userToUpdate);
    }

    @GetMapping("/current-user")
    @RolesAllowed("ROLE_USER")
    public UserMeta getUser(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getUserMeta();
    }
}
