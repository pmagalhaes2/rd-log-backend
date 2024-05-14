CREATE TABLE pharmaceutical (
                                id SERIAL PRIMARY KEY,
                                employee_id INT NOT NULL,
                                FOREIGN KEY (employee_id) REFERENCES Employee(id)
);