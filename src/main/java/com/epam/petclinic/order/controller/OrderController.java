package com.epam.petclinic.order.controller;

import com.epam.petclinic.order.domain.Order;
import com.epam.petclinic.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(name = "/", method = RequestMethod.POST)
    public Collection<UUID> saveOrder(@RequestBody Order order) {
        orderService.save(order);
        // TODO: generating clinic id list
        return Collections.singleton(order.getId());
    }

    // for test
    @RequestMapping(name = "/", method = RequestMethod.GET)
    public Collection<Order> getOrders() {
        return orderService.findAll();
    }
}
