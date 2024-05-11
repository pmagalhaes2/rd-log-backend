CREATE TABLE client(
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       cpf CHAR(11) NOT NULL UNIQUE,
                       gender CHAR(11),
                       date_of_birth DATE NOT NULL,
                       register_date DATE NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password CHAR(8) NOT NULL,
                       address_id INT NOT NULL,
                       phone_id INT NOT NULL,
                       FOREIGN KEY (address_id) REFERENCES Address(id),
                       FOREIGN KEY (phone_id) REFERENCES Phone(id)
);