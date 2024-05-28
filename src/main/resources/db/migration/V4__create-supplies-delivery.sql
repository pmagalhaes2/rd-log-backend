CREATE TABLE supplies_delivery(
    id SERIAL PRIMARY KEY,
    order_supplies_id INT NOT NULL,
    delivery_time TIMESTAMP NOT NULL,
    logistic_companies_id INT NOT NULL,
    status_id INT NOT NULL,
    FOREIGN KEY (logistic_companies_id) REFERENCES Logistic_companies(id),
    FOREIGN KEY (status_id) REFERENCES Status(id)
);