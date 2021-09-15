package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "privileges")
    @JsonIgnoreProperties("privileges")
    private Collection<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }
}