CREATE TABLE role
(
    id_authority BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK. Identificador unico definido com auto_increment.',
    PRIMARY KEY (id_authority)
) ENGINE = InnoDB COMMENT 'Tabela para armazenar usuarios.';