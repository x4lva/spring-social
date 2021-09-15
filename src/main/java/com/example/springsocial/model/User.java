package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column
    private String providerId;

    @Column
    private Boolean active;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("users")
    private Collection<Role> roles;


}
