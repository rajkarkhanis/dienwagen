package com.dienwagen.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
