package com.example.orderobjectmapper.service;

import com.example.orderobjectmapper.model.Customer;
import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;
import com.example.orderobjectmapper.repository.CustomerRepository;
import com.example.orderobjectmapper.repository.OrderRepository;
import com.example.orderobjectmapper.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Override
    public Product createNewProduct(Product product) {
        Product newProduct = Product.builder()
                .description(product.getDescription())
                .price(product.getPrice())
                .quantityInStock(product.getQuantityInStock())
                .build();
        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        Product updatedProduct = getProductById(product.getProductId());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantityInStock(product.getQuantityInStock());
        productRepository.save(updatedProduct);
        return updatedProduct;
    }

    @Override
    public Product deleteProductById(Long productId) {
        Product deletedProduct = getProductById(productId);
        productRepository.deleteById(productId);
        return deletedProduct;
    }

    @Override
    public Order createNewOrder(Order order) {
        Order newOrder = Order.builder()
                .orderDate(LocalDate.now())
                .products(order.getProducts())
                .customer(order.getCustomer())
                .build();
        newOrder.getProducts().addAll(order.getProducts());
        customerRepository.save(order.getCustomer());
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}
