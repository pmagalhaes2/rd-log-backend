CREATE TABLE products(
                         id SERIAL PRIMARY KEY,
                         comercial_name VARCHAR(255) NOT NULL,
                         active_principle VARCHAR(255) NOT NULL,
                         apresentation VARCHAR(255) NOT NULL,
                         batch VARCHAR(20) NOT NULL,
                         manufacturing_date DATE NOT NULL,
                         manufacturer VARCHAR(255) NOT NULL,
                         category_id INT NOT NULL,
                         price DECIMAL (10,2) NOT NULL,
                         volum INT NOT NULL,
                         dangerous BOOLEAN DEFAULT FALSE,
                         FOREIGN KEY (category_id) REFERENCES Category(id)
);