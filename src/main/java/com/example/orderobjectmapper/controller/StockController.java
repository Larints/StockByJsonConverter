package com.example.orderobjectmapper.controller;



import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;
import com.example.orderobjectmapper.service.JsonConverter;
import com.example.orderobjectmapper.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllProducts() {
        try {
            List<Product> products = stockService.getAllProducts();
            String result = JsonConverter.productsToJson(products);
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProductById(@RequestBody Long id) {
        try {
            Product product = stockService.getProductById(id);
            String result = JsonConverter.productToJson(product);
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewProduct(@RequestBody String productJson) {
        try {
            Product product = JsonConverter.jsonToProduct(productJson);
            Product createdProduct = stockService.createNewProduct(product);
            String result = JsonConverter.productToJson(createdProduct);
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProduct(@RequestBody String productJson) {
        try {
            Product product = JsonConverter.jsonToProduct(productJson);
            Product updatedProduct = stockService.updateProduct(product);
            String result = JsonConverter.productToJson(updatedProduct);
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@RequestBody Long productId) {
        try {
            Product deletedProduct = stockService.deleteProductById(productId);
            String result = JsonConverter.productToJson(deletedProduct);
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping (value = "/create_order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody String orderJson) {
           try {
               Order order = JsonConverter.jsonToOrder(orderJson);
               Order createdOrder = stockService.createNewOrder(order);
               return ResponseEntity.ok(JsonConverter.orderToJson(createdOrder));
           } catch (JsonProcessingException e) {
               return ResponseEntity.badRequest().build();
           }
    }

    @GetMapping(value = "/order/orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderById(@RequestBody Long orderId) {
        try {
            Order order = stockService.getOrderById(orderId);
            return ResponseEntity.ok(JsonConverter.orderToJson(order));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
