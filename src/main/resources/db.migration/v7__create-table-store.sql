CREATE TABLE store(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj CHAR(18) NOT NULL UNIQUE,
    adress_id INT NOT,
    phone_id INT NOT NULL,
    avisa_registration VARCHAR(50) NOT NULL,
    state_registration VARCHAR(50) NOT NULL,
    pharmaceutical_id INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    stock_id INT NOT NULL,
    update_date DATE NOT NULL,
    FOREIGN KEY (adress_id) REFERENCES Adress(id),
    FOREIGN KEY (phone_id) REFERENCES Phone(id),
    FOREIGN KEY (pharmaceutical_id) REFERENCES Pharmaceutical(id),
    FOREIGN KEY (stock_id) REFERENCES Stock(id)
);