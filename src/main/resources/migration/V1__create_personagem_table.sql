CREATE TABLE personagem(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    chakra INTEGER DEFAULT 100,
    vida INTEGER,
    dtype VARCHAR(31)
);

