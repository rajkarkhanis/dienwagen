package com.dienwagen.orders.service;

import com.dienwagen.orders.client.CustomerClient;
import com.dienwagen.orders.client.RequestsClient;
import com.dienwagen.orders.dto.VehicleTransactionDto;
import com.dienwagen.orders.entity.Customer;
import com.dienwagen.orders.entity.VehicleRequest;
import com.dienwagen.orders.entity.VehicleTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleTransactionService {
    private final CustomerClient customerClient;
    private final RequestsClient requestsClient;

    @Autowired
    public VehicleTransactionService(CustomerClient customerClient, RequestsClient requestsClient) {
        this.customerClient = customerClient;
        this.requestsClient = requestsClient;
    }

    public VehicleTransaction createTransaction(VehicleTransactionDto vehicleTransactionDto) {
        VehicleRequest request = findRequest(vehicleTransactionDto.getRequestId());
        Customer customer = findCustomer(vehicleTransactionDto.getCustomerId());

        VehicleTransaction transaction = new VehicleTransaction();
        transaction.setVehicleRequest(request);
        transaction.setCustomer(customer);

        return transaction;
    }

    // Get Customer from Customers Microservice
    public Customer findCustomer(Integer id) {
        return customerClient.findById(id).getBody();
    }

    // Get VehicleRequest from Requests Microservice
    public VehicleRequest findRequest(Integer id) {
        return requestsClient.findById(id).getBody();
    }
}
