CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price BIGINT,
    category VARCHAR(255),
    description VARCHAR(255),
    image_url VARCHAR(255),
    stock_quantity INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);