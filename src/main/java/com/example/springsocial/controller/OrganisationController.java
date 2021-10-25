package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Organisation;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.OrganisationRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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
    public Organisation getOrganisation(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal){
        Organisation res = organisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", userPrincipal));
        return res;
    }

    @PostMapping("/create")
    public Organisation createOrganisation(@RequestBody Organisation organisation, @CurrentUser UserPrincipal userPrincipal){
        organisation.setConfirmed(false);
        organisation.setAuthor(userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal)));
        organisationRepository.save(organisation);
        return organisation;
    }

    @PostMapping("/subscribe")
    public boolean subscribeOrganisation(@RequestBody Map<String, Long> data, @CurrentUser UserPrincipal userPrincipal){
        Organisation organisation = organisationRepository.findById(data.get("organisationId")).orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", userPrincipal));
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal));
        organisation.addSubscriber(user);

        organisationRepository.save(organisation);
        return organisation.getSubscribers().contains(user);
    }
}
