package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehiclePaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiclePaintRepository extends JpaRepository<VehiclePaint, Integer> {
    List<VehiclePaint> findByVehicleModelId(Integer modelId);
}