package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleBodyRepository extends JpaRepository<VehicleBody, Integer> {
    List<VehicleBody> findByVehicleModelName(String modelName);
    List<VehicleBody> findByType(String bodyType);
}