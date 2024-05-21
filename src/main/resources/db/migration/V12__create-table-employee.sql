CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    post VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password CHAR(8) NOT NULL,
    address_id INT NOT NULL,
    store_id INT,
    FOREIGN KEY (address_id) REFERENCES Address(id)
);