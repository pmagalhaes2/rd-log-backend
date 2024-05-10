CREATE TABLE store
(
    id     SERIAL PRIMARY KEY,
    ddi    CHAR(2) NOT NULL,
    ddd    CHAR(2) NOT NULL,
    number CHAR(9) NOT NULL
);