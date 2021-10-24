package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@With
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private boolean confirmed = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "organisation")
    @JsonIgnoreProperties("organisation")
    private Collection<Event> events;
}
