package com.dienwagen.orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vehicle_request")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VehicleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "vehicle_model")
    private VehicleModel vehicleModel;

    @OneToOne
    @JoinColumn(name = "vehicle_line")
    private VehicleLine vehicleLine;

    @OneToOne
    @JoinColumn(name = "vehicle_body")
    private VehicleBody vehicleBody;

    @OneToOne
    @JoinColumn(name = "vehicle_engine")
    private VehicleEngine vehicleEngine;

    @OneToOne
    @JoinColumn(name = "equipment_id")
    private VehicleEquipment vehicleEquipment;

    @OneToOne
    @JoinColumn(name = "int_paint_id")
    private VehiclePaint interiorPaint;

    @OneToOne
    @JoinColumn(name = "ext_paint_id")
    private VehiclePaint exteriorPaint;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleRequest that = (VehicleRequest) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}