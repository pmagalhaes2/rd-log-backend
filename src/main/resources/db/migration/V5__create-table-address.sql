CREATE TABLE address(
                        id SERIAL PRIMARY KEY,
                        type VARCHAR(50) NOT NULL,
                        value VARCHAR(255) NOT NULL,
                        number INT NOT NULL,
                        city VARCHAR (50) NOT NULL,
                        state VARCHAR(50) NOT NULL,
                        zip_code CHAR(8) NOT NULL

);