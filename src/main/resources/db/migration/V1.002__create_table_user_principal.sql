CREATE TABLE userPrincipal
(
    id_user         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK. Identificador unico definido com auto_increment.',
    name            VARCHAR(255)        NOT NULL COMMENT 'Indicador do nome do usuario.',
    password        VARCHAR(255)        NOT NULL COMMENT 'senha criptografada',
    id_authority    BIGINT(20) UNSIGNED NOT NULL COMMENT 'Data de criacao do registro.',
    email           VARCHAR(45)         NOT NULL COMMENT 'email ultilzado',
    PRIMARY KEY (id_user),
    UNIQUE  KEY (email, id_user),
    FOREIGN KEY (id_authority) REFERENCES role (id_authority)
) ENGINE = InnoDB COMMENT 'Tabela para armazenar usuarios.';