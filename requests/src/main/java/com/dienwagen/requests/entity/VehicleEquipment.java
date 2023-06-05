package com.dienwagen.requests.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vehicle_equipment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VehicleEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "equipment_id", nullable = false)
    private Integer id;

    @Column(name = "equipment_name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel vehicleModel;

    @OneToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private VehicleEngine vehicleEngine;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleEquipment that = (VehicleEquipment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}