CREATE TABLE client_orders(
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    store_id INT NOT NULL,
    date_order DATE NOT NULL,
    price_total DECIMAL(10,2) NOT NULL,
    payment_id INT NOT NULL,
    total_volum INT NOT NULL,
    delivery_type_id INT NOT NULL,
    dangerous BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (store_id) REFERENCES Store(id),
    FOREIGN KEY (payment_id) REFERENCES Payment(id),
    FOREIGN KEY (delivery_type_id) REFERENCES Delivery_type(id)
);