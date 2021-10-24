package com.example.springsocial.controller;

import com.example.springsocial.model.Event;
import com.example.springsocial.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController implements API {

    public final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/get/{id}")
    public Long getEvent(@PathVariable Long id){
        return id;
    }

    @GetMapping("/create")
    public Event createEvent(Event event){
        eventRepository.save(event);
        return event;
    }
}
