CREATE TABLE supplier_stock(
                               id SERIAL PRIMARY KEY,
                               products_id INT NOT NULL,
                               FOREIGN KEY (products_id) REFERENCES Products(id)

);
