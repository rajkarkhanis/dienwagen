package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleLineRepository extends JpaRepository<VehicleLine, Integer> {
    List<VehicleLine> findByVehicleModelName(String modelName);
    List<VehicleLine> findByVehicleEngineName(String engineName);
}