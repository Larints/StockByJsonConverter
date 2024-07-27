package com.example.orderobjectmapper.repository;

import com.example.orderobjectmapper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
