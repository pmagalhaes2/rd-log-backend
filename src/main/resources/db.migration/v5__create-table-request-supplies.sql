CREATE TABLE request_supplies (
    id SERIAL PRIMARY KEY,
    manufacturer_id INT  NOT NULL,
    order_date DATE NOT NULL,
    products_id INT  NOT NULL,
    -- Comentei pois não faz sentido a manutenção já que temos o pagamento abaixo price_total DECIMAL(10, 2),
    payment_id INT NOT NULL,
    FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer(id),
    FOREIGN KEY (products_id) REFERENCES Products(id),
    FOREIGN KEY (payment_id) REFERENCES Payment(id),
);