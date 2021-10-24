package com.example.springsocial.repository;

import com.example.springsocial.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
