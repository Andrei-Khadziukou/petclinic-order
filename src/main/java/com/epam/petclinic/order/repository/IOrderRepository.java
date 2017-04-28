package com.epam.petclinic.order.repository;

import com.epam.petclinic.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {
}
