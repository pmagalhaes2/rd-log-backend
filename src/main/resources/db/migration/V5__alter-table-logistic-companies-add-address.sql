ALTER TABLE logistic_companies ADD COLUMN address_id INT;

ALTER TABLE logistic_companies
    ADD CONSTRAINT fk_address
        FOREIGN KEY (address_id) REFERENCES address(id);