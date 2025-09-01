CREATE TABLE jutsu (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    dano INTEGER NOT NULL,
    consumo_de_chakra INTEGER NOT NULL
);
