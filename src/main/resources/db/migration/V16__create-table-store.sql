CREATE TABLE store (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       cnpj CHAR(18) NOT NULL UNIQUE,
                       address_id INT NOT NULL,
                       phone_id INT NOT NULL,
                       avisa_registration VARCHAR(50) NOT NULL,
                       state_registration VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       opening_hours TIME NOT NULL,
                       closing_hours TIME NOT NULL,
                       stock_store_id INT NOT NULL,
                       update_date DATE NOT NULL,
                       pharmacist_id INT, -- Adicionando o campo opcional para o farmacêutico responsável
                       FOREIGN KEY (address_id) REFERENCES Address(id),
                       FOREIGN KEY (phone_id) REFERENCES Phone(id),
                       FOREIGN KEY (pharmacist_id) REFERENCES Pharmaceutical(id), -- Adicionando a chave estrangeira opcional
                       FOREIGN KEY (stock_store_id) REFERENCES Stock_store(id)
);