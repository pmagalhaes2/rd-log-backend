CREATE TABLE delivery_type (
    id SERIAL PRIMARY KEY,
    size VARCHAR(50) NOT NULL, -- Tamanho da carga (pequeno, médio, grande, etc.)
    vehicle_type VARCHAR(50) NOT NULL -- Tipo de veículo (MOTO, CARRO, CAMINHÃO)
);