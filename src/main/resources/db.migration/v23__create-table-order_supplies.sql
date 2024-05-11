CREATE TABLE order_supplies(
                               id SERIAL PRIMARY KEY,
                               manufacturer_id INT not null,
                               date_order DATE NOT NULL,
                               products_id INT NOT NULL,
                               payment_id INT NOT NULL,
                               price_total DECIMAL(10,2) NOT NULL,
                               total_volum INT NOT NULL,
                               dangerous BOOLEAN DEFAULT FALSE,
                               delivery_type_id INT NOT NULL,
                               logistic_companies_id INT NOT NULL,
                               FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer(id),
                               FOREIGN KEY (products_id) REFERENCES Products(id),
                               FOREIGN KEY (payment_id) REFERENCES Payment(id),
                               FOREIGN KEY (delivery_type_id) REFERENCES Delivery_type(id),
                               FOREIGN KEY (logistic_companies_id) REFERENCES Logistic_companies(id)
);