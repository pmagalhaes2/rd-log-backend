CREATE TABLE logistic_companies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj CHAR(14) UNIQUE NOT NULL,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    accepts_dangerous_loads BOOLEAN DEFAULT FALSE
);

