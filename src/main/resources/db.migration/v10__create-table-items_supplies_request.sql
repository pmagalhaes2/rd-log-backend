CREATE TABLE items_supplies_request(
                                       id SERIAL PRIMARY KEY,
                                       products_id INT NOT NULL,
                                       request_supplies_id INT NOT NULL,
                                       quantity INT,
                                       price_total DECIMAL (10,2),
                                       FOREIGN KEY (products_id) REFERENCES Products(id),
                                       FOREIGN KEY (request_supplies_id) REFERENCES Request_supplies(id)
);