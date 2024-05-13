CREATE TABLE phone(
                      id     SERIAL PRIMARY KEY,
                      ddi    CHAR(2) NOT NULL,
                      ddd    CHAR(2) NOT NULL,
                      phonenumber CHAR(9) NOT NULL
);