package com.dienwagen.orders.service;

import com.dienwagen.orders.dto.OrderDto;
import com.dienwagen.orders.entity.Order;
import com.dienwagen.orders.exception.ExceptionSupplier;
import com.dienwagen.orders.exception.ResourceNotFoundException;
import com.dienwagen.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final VehicleTransactionService transactionService;

    @Autowired
    public OrderService(OrderRepository orderRepository, VehicleTransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.transactionService = transactionService;
    }

    // Save an Order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Find Order by id
    // Return order status of found Order
    // If no order found, throw exception
    public String getOrderStatus(Integer id) throws ResourceNotFoundException {
        Order foundOrder = orderRepository.findById(id).orElseThrow(ExceptionSupplier.RESOURCE_NOT_FOUND_EXCEPTION);
        return foundOrder.getOrderStatus();
    }

    // Convert DTO to Entity
    public Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setTransaction(transactionService.createTransaction(orderDto.getVehicleTransaction()));
        order.setOrderDate(orderDto.getOrderDate());
        order.setEstDeliveryDate(orderDto.getEstDeliveryDate());
        order.setActualDeliveryDate(orderDto.getActualDeliveryDate());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setTransportCost(orderDto.getTransportCost());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
}
