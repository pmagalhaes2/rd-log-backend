CREATE TABLE manufacturer(
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             cnpj CHAR(14) NOT NULL UNIQUE,
                             nire CHAR (11) NOT NULL UNIQUE,
                             email VARCHAR (255) NOT NULL UNIQUE,
                             phone_id INT NOT NULL,
                             address_id INT NOT NULL,
                             supplier_stock_id INT NOT NULL,
                             cost DECIMAL(10,2) NOT NULL,
                             FOREIGN KEY (phone_id) REFERENCES Phone(id),
                             FOREIGN KEY (address_id) REFERENCES Address(id),
                             FOREIGN KEY (supplier_stock_id) REFERENCES Supplier_stock(id)

);