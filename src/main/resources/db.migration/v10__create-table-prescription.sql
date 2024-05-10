CREATE TABLE prescription(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    order_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Order(id)
);