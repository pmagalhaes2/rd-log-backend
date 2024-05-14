CREATE TABLE prescription(
                             id SERIAL PRIMARY KEY,
                             description VARCHAR(100) NOT NULL,
                             client_orders_id INT NOT NULL,
                             FOREIGN KEY (client_orders_id) REFERENCES Client_orders(id)
);