package com.epam.petclinic.order.controller;

import com.epam.petclinic.order.api.ClinicApi;
import com.epam.petclinic.order.api.domain.Clinic;
import com.epam.petclinic.order.domain.Order;
import com.epam.petclinic.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Resource
    private ClinicApi clinicApi;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<UUID> saveOrder(@RequestBody Order order) {
        orderService.save(order);
        return clinicApi.getClinicIdByAnimalIdAndServiceIds(order.getAnimalId(), order.getServiceIds());
    }

    // example of communication between services with authenticated client
    @RequestMapping(value = "/clinic-test", method = RequestMethod.GET)
    public Collection<Clinic> testClinicApi() {
        return clinicApi.getClinics();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Order> getOrders() {
        return orderService.findAll();
    }

    public void setClinicApi(ClinicApi clinicApi) {
        this.clinicApi = clinicApi;
    }
}
