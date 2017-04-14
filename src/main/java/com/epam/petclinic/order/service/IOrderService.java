package com.epam.petclinic.order.service;

import com.epam.petclinic.order.domain.Order;

import java.util.List;

public interface IOrderService {
    void save(Order order);

    List<Order> findAll();
}
