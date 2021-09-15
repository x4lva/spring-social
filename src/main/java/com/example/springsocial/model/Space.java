package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "users_spaces",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "space_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("spaces")
    private Collection<Role> space;
}
