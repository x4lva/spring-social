package com.example.springsocial.repository;

import com.example.springsocial.model.Privilege;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    Privilege findByName(String name);

}
