package com.example.orderobjectmapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.orderobjectmapper.model.Customer;
import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.List;

public class JsonConverter {

    private static final ObjectMapper objectMapper;

    static {
         objectMapper = new ObjectMapper();
         objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
         objectMapper.registerModule(new JavaTimeModule());
         objectMapper.registerModule(new ParameterNamesModule());
    }

    // Сериализация объекта Customer в JSON
    public static String customerToJson(Customer customer) throws JsonProcessingException {
        return objectMapper.writeValueAsString(customer);
    }

    // Десериализация JSON в объект Customer
    public static Customer jsonToCustomer(String json) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(json, Customer.class);
    }

    // Сериализация объекта Order в JSON
    public static String orderToJson(Order order) throws JsonProcessingException {
        return objectMapper.writeValueAsString(order);
    }

    // Десериализация JSON в объект Order
    public static Order jsonToOrder(String json) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(json, Order.class);
    }

    // Сериализация объекта Product в JSON
    public static String productToJson(Product product) throws JsonProcessingException {
        return objectMapper.writeValueAsString(product);
    }

    // Десериализация JSON в объект Product
    public static Product jsonToProduct(String json) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(json, Product.class);
    }

    // Сериализация списка объектов Product в JSON
    public static String productsToJson(List<Product> products) throws JsonProcessingException {
        return objectMapper.writeValueAsString(products);
    }

}

