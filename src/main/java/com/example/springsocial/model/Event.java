package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    @JsonIgnoreProperties("events")
    private Organisation organisation;
}
