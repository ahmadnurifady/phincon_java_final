CREATE TABLE IF NOT EXISTS transaction (
    id SERIAL PRIMARY KEY,
    amount FLOAT,
    order_id FLOAT,
    payment_date TIMESTAMP,
    mode VARCHAR(255),
    status VARCHAR(255),
    reference_number VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS balance (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    amount FLOAT
);