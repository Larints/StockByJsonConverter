CREATE TABLE  customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    contact_number VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    order_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);


CREATE TABLE products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity_in_stock INT NOT NULL
);

CREATE TABLE order_products (
    order_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
