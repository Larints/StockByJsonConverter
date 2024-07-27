INSERT INTO customers (first_name, last_name, email, contact_number)
VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890'),
       ('Jane', 'Smith', 'jane.smith@example.com', '0987654321');


INSERT INTO products (description, price, quantity_in_stock)
VALUES ('Product 1', 10.99, 100), ('Product 2', 20.99, 50);

INSERT INTO orders (customer_id, order_date) VALUES (1, '2023-10-01'), (2, '2023-10-02');


INSERT INTO order_products (order_id, product_id) VALUES (1, 1), (1, 2), (2, 1);

