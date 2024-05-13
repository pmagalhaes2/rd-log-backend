CREATE TABLE payment(
                        id SERIAL PRIMARY KEY,
                        payment_type VARCHAR(50) NOT NULL,
                        value DECIMAL (10,2) NOT NULL,
                        payment_date DATE NOT NULL

);