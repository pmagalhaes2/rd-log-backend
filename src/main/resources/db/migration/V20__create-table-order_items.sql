CREATE TABLE order_items(
                            id SERIAL PRIMARY KEY,
                            order_id INT NOT NULL,
                            products_id INT NOT NULL,
                            quantity_items INT NOT NULL,
                            amount INT NOT NULL,
                            total_volume INT NOT NULL,
                            FOREIGN KEY (products_id) REFERENCES Products(id)
);