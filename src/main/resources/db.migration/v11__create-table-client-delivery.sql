CREATE TABLE client_delivery(
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    volum DECIMAL (10,2) NOT NULL, -- No pedido já existe essa informação. Precisa repetir?
    delivery_time TIMESTAMP NOT NULL, --DATETIME não foi aceito
    logistic_companies_id INT NOT NULL,
    status_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Order(id),
    FOREIGN KEY (logistic_companies_id) REFERENCES Logistic_companies(id),
    FOREIGN KEY (status_id) REFERENCES Status(id)
);