package com.dienwagen.requests.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "vehicle_body")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VehicleBody {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "body_id", nullable = false)
    private Integer id;

    @Column(name = "body_type", nullable = false)
    private String type;

    @OneToOne
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel vehicleModel;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_time")
    private String lastUpdatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleBody that = (VehicleBody) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}