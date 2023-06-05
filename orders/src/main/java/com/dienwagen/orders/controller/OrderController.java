package com.dienwagen.orders.controller;

import com.dienwagen.orders.dto.OrderDto;
import com.dienwagen.orders.entity.Order;
import com.dienwagen.orders.exception.ResourceNotFoundException;
import com.dienwagen.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Search for Order status by Order number
    @GetMapping(path = "/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(orderService.getOrderStatus(id), HttpStatus.OK);
    }

    // Save an Order
    @PostMapping
    public ResponseEntity<Integer> saveOrder(@RequestBody OrderDto orderDto) {
        Order savedOrder = orderService.saveOrder(orderService.convertToEntity(orderDto));
        return new ResponseEntity<>(savedOrder.getId(), HttpStatus.OK);
    }
}
