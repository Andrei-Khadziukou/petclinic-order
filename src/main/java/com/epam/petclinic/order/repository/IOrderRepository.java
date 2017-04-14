package com.epam.petclinic.order.repository;

import com.epam.petclinic.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, String> {
}
