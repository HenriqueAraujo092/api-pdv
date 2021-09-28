CREATE TABLE customer (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    active TINYINT(1) NOT NULL DEFAULT '1',
    street VARCHAR(50),
    number VARCHAR(11),
    complement VARCHAR(50),
    neighborhood VARCHAR(50),
    cep VARCHAR(11),
    city VARCHAR(50),
    state VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO customer (name, street, cep) VALUES ('Vendedor padrão', 'Doze de julho', '60520590');