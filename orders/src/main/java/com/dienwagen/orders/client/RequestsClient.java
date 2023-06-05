package com.dienwagen.orders.client;

import com.dienwagen.orders.entity.VehicleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "requests", url = "http://localhost:8080/requests")
public interface RequestsClient {
    @GetMapping(path = "{id}")
    ResponseEntity<VehicleRequest> findById(@PathVariable Integer id);
}
