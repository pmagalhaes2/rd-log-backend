CREATE TABLE logistic_companies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj CHAR(14) UNIQUE NOT NULL,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(8) NOT NULL

);