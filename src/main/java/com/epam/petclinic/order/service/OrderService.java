package com.epam.petclinic.order.service;

import com.epam.petclinic.order.domain.Order;
import com.epam.petclinic.order.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Transactional
    @Override
    public void save(Order order) {
        order.setId(UUID.randomUUID().toString());
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
