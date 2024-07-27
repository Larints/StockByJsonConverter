package com.example.orderobjectmapper.repository;

import com.example.orderobjectmapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
