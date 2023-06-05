package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleEquipmentRepository extends JpaRepository<VehicleEquipment, Integer> {
    List<VehicleEquipment> findByVehicleModelId(Integer modelId);
}