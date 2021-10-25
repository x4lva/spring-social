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

    @Lob
    private String description = "";

    @Column
    private boolean confirmed = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToMany(mappedBy = "subscribedOrganisations")
    @JsonIgnoreProperties("subscribedOrganisations")
    private Collection<User> subscribers;

    @OneToMany(mappedBy = "organisation")
    @JsonIgnoreProperties("organisation")
    private Collection<Event> events;

    public boolean addSubscriber(User user) {
        if (user == null) return false;

        if (this.subscribers.contains(user)) {
            this.subscribers.remove(user);
            user.getSubscribedOrganisations().remove(this);
            return true;
        }

        this.subscribers.add(user);
        user.getSubscribedOrganisations().add(this);

        return true;
    }
}
