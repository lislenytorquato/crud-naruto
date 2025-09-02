ALTER TABLE personagem_jutsus DROP CONSTRAINT personagem_jutsus_jutsu_nome_fkey;

ALTER TABLE personagem_jutsus RENAME COLUMN jutsu_nome TO jutsu_id;

ALTER TABLE personagem_jutsus ALTER COLUMN jutsu_id TYPE BIGINT USING jutsu_id::bigint;

ALTER TABLE personagem_jutsus
ADD CONSTRAINT fk_jutsu_id FOREIGN KEY (jutsu_id) REFERENCES jutsu(id);

ALTER TABLE personagem_jutsus DROP CONSTRAINT personagem_jutsus_pkey;

ALTER TABLE personagem_jutsus ADD PRIMARY KEY (personagem_id, jutsu_id);





