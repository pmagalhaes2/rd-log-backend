ALTER TABLE logistic_companies_id ADD COLUMN address INT;

ALTER TABLE logistic_companies_id ADD CONSTRAINT fk_address FOREIGN KEY(address_id) REFERENCES address (id);