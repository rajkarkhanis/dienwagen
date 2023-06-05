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
@Table(name = "vehicle_paint")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VehiclePaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paint_id", nullable = false)
    private Integer id;

    @Column(name = "paint_name", nullable = false)
    private String name;

    @Column(name = "paint_type", nullable = false)
    private String type;

    @OneToOne
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel vehicleModel;

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
        VehiclePaint that = (VehiclePaint) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}