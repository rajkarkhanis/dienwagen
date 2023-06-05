package com.dienwagen.orders.client;

import com.dienwagen.orders.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customers", url = "http://localhost:8090/customers")
public interface CustomerClient {
    @GetMapping(path = "{id}")
    ResponseEntity<Customer> findById(@PathVariable Integer id);
}
