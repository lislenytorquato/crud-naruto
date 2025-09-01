CREATE TABLE personagem_jutsus (
    personagem_id BIGINT NOT NULL,
    jutsu_nome VARCHAR(255) NOT NULL,
    FOREIGN KEY (personagem_id) REFERENCES personagem(id),
    FOREIGN KEY (jutsu_nome) REFERENCES jutsu(nome),
    PRIMARY KEY (personagem_id, jutsu_nome)
);
