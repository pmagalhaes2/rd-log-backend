CREATE TABLE logistic_companies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    cnpj CHAR(14) UNIQUE NOT NULL,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    --phone_number VARCHAR(11) NOT NULL,
    email VARCHAR(100) NOT NULL,
    accepts_dangerous_loads BOOLEAN DEFAULT FALSE
);

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    comercial_name VARCHAR(255) NOT NULL,
    active_principle VARCHAR(255) NOT NULL,
    apresentation VARCHAR(255) NOT NULL,
    batch VARCHAR(20) NOT NULL,
    manufacturing_date DATE NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    price DECIMAL (10,2) NOT NULL,
    volum NUMBER (10,2),
    dangerous BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES Category(id));

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE items_supplies_request(
    id SERIAL PRIMARY KEY,
    products_id INT NOT NULL,
    request_supplies_id INT NOT NULL,
    quantity INT,
    price_total DECIMAL (10,2),
    FOREIGN KEY (products_id) REFERENCES Products(id),
    FOREIGN KEY (request_supplies_id) REFERENCES Request_supplies(id));

CREATE TABLE request_supplies (
    id SERIAL PRIMARY KEY,
    manufacturer_id INT  NOT NULL,
    order_date DATE NOT NULL,
    products_id INT  NOT NULL,
    price_total DECIMAL(10, 2),
    FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer (id),
    FOREIGN KEY (products_id) REFERENCES Products (id),
);

CREATE TABLE status(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,

);

CREATE TABLE store(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    adress VARCHAR(100) NOT NULL UNIQUE,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    email VARCHAR(100) NOT NULL,
    accepts_dangerous_loads BOOLEAN DEFAULT FALSE
);

CREATE TABLE order(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    adress VARCHAR(100) NOT NULL UNIQUE,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL,
    email VARCHAR(100) NOT NULL,
    accepts_dangerous_loads BOOLEAN DEFAULT FALSE
);

CREATE TABLE purchasedelivery(
    id SERIAL PRIMARY KEY,
    store_id INT NOT NULL,
    purchasinglist TEXT NOT NULL,
    FOREIGN KEY (store_id) REFERENCES Store(id));