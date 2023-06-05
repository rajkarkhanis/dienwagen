package com.dienwagen.customers.service;

import com.dienwagen.customers.dto.CustomerSearchDto;
import com.dienwagen.customers.entity.Customer;
import com.dienwagen.customers.exception.ExceptionSupplier;
import com.dienwagen.customers.exception.RecordAlreadyExistsException;
import com.dienwagen.customers.exception.RecordNotFoundException;
import com.dienwagen.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Return all Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Add a new Customer
    public Customer addCustomer(Customer customer) throws RecordAlreadyExistsException {
        // If customer exists, throw Exception
        if (customerRepository.exists(Example.of(customer))) {
            throw new RecordAlreadyExistsException("Customer already exists");
        }
        return customerRepository.save(customer);
    }

    // Get Customer by ID
    public Customer findById(Integer id) throws RecordNotFoundException {
        return customerRepository.findById(id).orElseThrow(ExceptionSupplier.RECORD_NOT_FOUND_EXCEPTION);
    }

    // Get Customers by first name
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    // Get Customers by last name
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public List<Customer> searchCustomers(CustomerSearchDto searchDto) throws RecordNotFoundException {
        List<Customer> customers = new ArrayList<>();

        // if ID is present call find by id
        if (searchDto.getCustomerId() != null) {
            Customer foundCustomer = findById(searchDto.getCustomerId());
            return List.of(foundCustomer);
        }

        // if first name or last name is specified, find by those
        if (searchDto.getLastName() != null) {
            customers.addAll(findByLastName(searchDto.getLastName()));
        }

        if (searchDto.getFirstName() != null) {
            customers.addAll(findByFirstName(searchDto.getFirstName()));
        }
        return customers;
    }
}
