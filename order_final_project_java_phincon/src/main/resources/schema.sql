CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    order_date TIMESTAMP,
    billing_address VARCHAR(255),
    order_status VARCHAR(50),
    payment_method VARCHAR(50),
    shipping_address VARCHAR(255),
    total_amount FLOAT
);


CREATE TABLE IF NOT EXISTS order_item (
    id SERIAL PRIMARY KEY,
    price FLOAT,
    product_id INTEGER,
    quantity INTEGER,
    order_id INTEGER,
    FOREIGN KEY(order_id) REFERENCES orders(id)
);