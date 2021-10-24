package com.example.springsocial.repository;

import com.example.springsocial.model.Organisation;
import org.springframework.data.repository.CrudRepository;

public interface OrganisationRepository extends CrudRepository<Organisation, Long> {
}
