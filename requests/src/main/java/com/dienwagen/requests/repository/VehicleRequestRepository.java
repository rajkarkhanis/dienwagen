package com.dienwagen.requests.repository;

import com.dienwagen.requests.entity.VehicleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRequestRepository extends JpaRepository<VehicleRequest, Integer> {
}