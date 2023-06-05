package com.dienwagen.customers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CustomerDto {
    private Integer id;

    @NotEmpty(message = "Please enter first name")
    private String firstName;

    @NotEmpty(message = "Please enter last name")
    private String lastName;

    @NotEmpty(message = "Please enter phone number")
    @Size(min = 10, max = 10, message = "Phone number should be 10 digits")
    private String phone;

    @NotEmpty(message = "Please enter email address")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Please enter address")
    private String address;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String firstName, String lastName, String phone, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}