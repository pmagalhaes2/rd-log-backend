ALTER TABLE orders ADD COLUMN logistic_company_id INT;

ALTER TABLE orders ADD CONSTRAINT fk_logistic_companies FOREIGN KEY(logistic_company_id) REFERENCES logistic_companies (id);