package com.example.orderobjectmapper;

import com.example.orderobjectmapper.model.Customer;
import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;
import com.example.orderobjectmapper.service.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class OrderObjectMapperApplication {

    public static void main(String[] args) throws JsonProcessingException {

        SpringApplication.run(OrderObjectMapperApplication.class, args);
    }

}
