ALTER TABLE employee ADD COLUMN logistic_companies_id INT;

ALTER TABLE employee ADD CONSTRAINT fk_logistic_companies FOREIGN KEY(logistic_companies_id) REFERENCES logistic_companies (id);



