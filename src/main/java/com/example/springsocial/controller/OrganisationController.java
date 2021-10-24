package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Organisation;
import com.example.springsocial.repository.OrganisationRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {
    public final OrganisationRepository organisationRepository;
    public final UserRepository userRepository;

    public OrganisationController(OrganisationRepository organisationRepository, UserRepository userRepository) {
        this.organisationRepository = organisationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/get/{id}")
    public Organisation getEvent(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal){
        Organisation res = organisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", userPrincipal));
        return res;
    }

    @PostMapping("/create")
    public Organisation createEvent(@RequestBody Organisation organisation, @CurrentUser UserPrincipal userPrincipal){
        organisation.setConfirmed(false);
        organisation.setAuthor(userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal)));
        organisationRepository.save(organisation);
        return organisation;
    }
}
