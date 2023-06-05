package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleEngine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleEngineRepository extends JpaRepository<VehicleEngine, Integer> {
    List<VehicleEngine> getEnginesByName(String name);
}