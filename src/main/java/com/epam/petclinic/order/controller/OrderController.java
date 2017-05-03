package com.epam.petclinic.order.controller;

import com.epam.petclinic.order.domain.Order;
import com.epam.petclinic.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<UUID> saveOrder(@RequestBody Order order) {
        orderService.save(order);
        // TODO: generating clinic id list
        return Collections.singleton(order.getId());
    }

    // for test
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Order> getOrders() {
        return orderService.findAll();
    }
}
