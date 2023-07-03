-- Creación de la tabla
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS purchase (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    version VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    cryptocurrency VARCHAR(255) NOT NULL,
    price_usd NUMERIC(10, 2) NOT NULL,
    price_cryptocurrency NUMERIC(10, 2) NOT NULL,
    date TIMESTAMP NOT NULL
);

-- Inserción de dato

