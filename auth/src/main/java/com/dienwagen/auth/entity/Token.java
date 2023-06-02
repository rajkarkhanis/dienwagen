package com.dienwagen.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String value;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Token token = (Token) o;
        return id != null && Objects.equals(id, token.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
