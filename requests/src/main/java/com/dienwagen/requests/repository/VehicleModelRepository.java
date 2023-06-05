package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
    List<VehicleModel> getVehicleModelsByName(String name);
}