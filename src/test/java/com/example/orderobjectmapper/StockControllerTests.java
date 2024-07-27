package com.example.orderobjectmapper;

import com.example.orderobjectmapper.model.Customer;
import com.example.orderobjectmapper.model.Order;
import com.example.orderobjectmapper.model.Product;
import com.example.orderobjectmapper.service.StockService;
import com.example.orderobjectmapper.controller.StockController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
@ExtendWith(SpringExtension.class)
public class StockControllerTests {


    @MockBean
    private StockService stockService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product = new Product(1L, "Product 1", 10.99, 100);
        when(stockService.getAllProducts()).thenReturn(Collections.singletonList(product));
        
        mockMvc.perform(get("/api/v1/stock")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].description").value("Product 1"));
        
        verify(stockService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", 10.99, 100);
        when(stockService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/v1/stock/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(1L)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.description").value("Product 1"));

        verify(stockService, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateNewProduct() throws Exception {
        Product product = new Product(null, "New Product", 15.99, 50);
        Product createdProduct = new Product(1L, "New Product", 15.99, 50);
        when(stockService.createNewProduct(any(Product.class))).thenReturn(createdProduct);
        
        mockMvc.perform(post("/api/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.description").value("New Product"));
        
        verify(stockService, times(1)).createNewProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Updated Product", 20.99, 25);
        when(stockService.updateProduct(any(Product.class))).thenReturn(product);
        
        mockMvc.perform(put("/api/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Updated Product"));
        
        verify(stockService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product(1L, "Product to Delete", 10.99, 100);
        when(stockService.deleteProductById(1L)).thenReturn(product);
        
        mockMvc.perform(delete("/api/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(1L)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Product to Delete"));
        
        verify(stockService, times(1)).deleteProductById(1L);
    }


    @Test
    public void testCreateOrder() throws Exception {
        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .contactNumber("1234567890")
                .build();

        Product product1 = Product.builder()
                .description("Product 1")
                .price(100.0)
                .quantityInStock(10)
                .build();

        Product product2 = Product.builder()
                .description("Product 2")
                .price(200.0)
                .quantityInStock(5)
                .build();

        Order order = Order.builder()
                .orderDate(LocalDate.now())
                .customer(customer)
                .products(Arrays.asList(product1, product2))
                .build();

        Order createdOrder = Order.builder()
                .orderId(1L)
                .orderDate(LocalDate.now())
                .customer(customer)
                .products(Arrays.asList(product1, product2))
                .build();

        when(stockService.createNewOrder(any(Order.class))).thenReturn(createdOrder);

        mockMvc.perform(post("/api/v1/stock/create_order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.customer.firstName").value("John"))
                .andExpect(jsonPath("$.products[0].description").value("Product 1"));

        verify(stockService, times(1)).createNewOrder(any(Order.class));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .contactNumber("1234567890")
                .build();

        Product product1 = Product.builder()
                .description("Product 1")
                .price(100.0)
                .quantityInStock(10)
                .build();

        Product product2 = Product.builder()
                .description("Product 2")
                .price(200.0)
                .quantityInStock(5)
                .build();

        Order order = Order.builder()
                .orderId(1L)
                .orderDate(LocalDate.now())
                .customer(customer)
                .products(Arrays.asList(product1, product2))
                .build();

        when(stockService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/v1/stock/order/orderId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(1L)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.customer.firstName").value("John"))
                .andExpect(jsonPath("$.products[0].description").value("Product 1"));

        verify(stockService, times(1)).getOrderById(1L);
    }
}
