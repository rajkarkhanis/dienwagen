package com.dienwagen.customers.controller;

import com.dienwagen.customers.dto.CustomerDto;
import com.dienwagen.customers.dto.CustomerSearchDto;
import com.dienwagen.customers.entity.Customer;
import com.dienwagen.customers.exception.RecordAlreadyExistsException;
import com.dienwagen.customers.exception.RecordNotFoundException;
import com.dienwagen.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper mapper = new ModelMapper();

    // GET All Customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers().stream()
                .map(customer -> mapper.map(customer, CustomerDto.class)).toList();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<List<CustomerDto>> searchCustomers(@RequestBody CustomerSearchDto customerSearchDto) throws RecordNotFoundException {
        List<CustomerDto> customersToReturn = customerService.searchCustomers(customerSearchDto)
                .stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .toList();
        return new ResponseEntity<>(customersToReturn, HttpStatus.OK);
    }

    // POST Add new Customer
    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody @Valid CustomerDto customerDto) throws RecordAlreadyExistsException {
        Customer customer = customerService.addCustomer(mapper.map(customerDto, Customer.class));
        return new ResponseEntity<>(mapper.map(customer, CustomerDto.class), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) throws RecordNotFoundException {
        Customer foundCustomer = customerService.findById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }
}
