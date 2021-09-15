package com.example.springsocial.repository;

import com.example.springsocial.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

}
