package com.example.orderobjectmapper.service;

import com.example.orderobjectmapper.model.Customer;
import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;

import java.util.List;

public interface StockService {

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product createNewProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProductById(Long productId);

    Order createNewOrder(Order order);

    Order getOrderById(Long orderId);

}
