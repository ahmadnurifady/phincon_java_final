CREATE TABLE IF NOT EXISTS cart (
    id SERIAL PRIMARY KEY NOT NULL,
    status VARCHAR(255),
    total_price FLOAT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);


CREATE TABLE IF NOT EXISTS cart_item (
    id SERIAL PRIMARY KEY NOT NULL,
    product_id INTEGER,
    quantity INTEGER,
    cart_id INTEGER
);

