CREATE TABLE board(
    id int(10) NOT NULL AUTO_INCREMENT,
    nome varchar(15) NOT NULL UNIQUE,
    PRIMARY KEY (nome)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE coluna(
    id int(10) NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    idBoard int(10) NOT NULL,
    tipo ENUM('iniciado', 'concluido', 'cancelado', 'pendente') NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_b FOREIGN KEY (idBoard)
    REFERENCES board(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE card(
    id int(10) NOT NULL AUTO_INCREMENT,
    titulo varchar(15) NOT NULL,
    descricao varchar(50),
    idColuna int(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_c FOREIGN KEY (idColuna)
    REFERENCES coluna(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE block(
    id int(10) NOT NULL AUTO_INCREMENT,
    ativo boolean NOT NULL,
    motivoBlock varchar(20) NOT NULL,
    motivoUnblock varchar(20),
    idCard int(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_card FOREIGN KEY (idCard)
    REFERENCES card(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;