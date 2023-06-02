package com.dienwagen.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "dealer_details")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Dealer {
    @Id
    @GeneratedValue
    @Column(name = "dealer_id")
    private Integer id;

    private String name;
    private String contact;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dealer dealer = (Dealer) o;
        return id != null && Objects.equals(id, dealer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
