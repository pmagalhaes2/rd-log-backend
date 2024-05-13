CREATE TABLE manufacturer_store (
                                    id SERIAL PRIMARY KEY,
                                    products_id INT NOT NULL,
                                    product_quantity INT NOT NULL,
                                    update_date DATE NOT NULL,
                                    FOREIGN KEY (products_id) REFERENCES Products(id)

);
