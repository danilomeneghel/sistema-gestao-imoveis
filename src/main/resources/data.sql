INSERT INTO categoria (nome) VALUES ('Casa'),('Apartamento'),('Condomínio'),('Chacára');
INSERT INTO negocio (nome) VALUES('Venda'),('Aluguel'),('Financiamento');

INSERT INTO quartos (descricao,quantidade) VALUES
('Um quarto de solteiro',1),('Um quarto de casal',1),
('Dois quartos de solteiro',2),('Dois quartos de casal',2),
('Um quarto de casal e um quarto de solteiro',2);

INSERT INTO estado (nome,uf) VALUES
('São Paulo','SP'),('Rio de Janeiro','RJ'),
('Rio Grande do Norte','RN'),('Minas Gerais','MG');

INSERT INTO municipio (nome,estado) VALUES
('Sorocaba',1),('Campinas',1),('São Paulo',1),
('Rio de Janeiro',2),('São Gonçalo',2),('Duque de Caxias',2),
('Natal',3),('Alexandria',3),('Angicos',3),
('Belo Horizonte',4),('Mariana',4),('Betim',4);

INSERT INTO bairro (nome,municipio) VALUES
('Campolim',1),('Mangal',1),('Além-Ponte',1),
('Botafogo',2),('Cambuí',2),('Guanabara',2),
('Sumaré',3),('Lapa',3),('Bela Vista',3),
('Cachambi',4),('Méier',4),('Jacarepaguá',4),
('Galo Branco',5),('Estrela do Norte',5),('Lindo Parque',5),
('Figueira',6),('Pantanal',6),('Campos Elísios',6),
('Guarapes',7),('Igapó',7),('Lagoa Azul',7),
('Cascalho',8),('Conjunto da Ponte',8),('Santo Antônio',8),
('Fernando Pedrosa',9),('Alto da Alegria',9),('Prefeito Jaime Batista',9),
('Savassi',10),('Lourdes',10),('Pampulha',10),
('Catete',11),('Barro Preto',11),('Galego',11),
('Alterosas',12),('Imbiruçu',12),('Petrovale',12);

INSERT INTO imovel (categoria_imovel,negocio_imovel,quarto_imovel,valor,bairro_imovel) VALUES
(1,1,5,500000,1),
(1,2,2,2000,2),
(1,3,3,20000,3),
(2,1,4,400000,4),
(2,1,1,370000,5),
(2,2,1,1700,6),
(3,2,4,2300,7),
(3,3,3,45000,8),
(3,3,2,56000,9),
(4,1,5,650000,10),
(4,1,2,345000,11),
(4,1,4,290000,12),
(1,2,3,3000,13),
(1,2,5,1200,14),
(1,2,1,900,15),
(2,3,3,16000,16),
(2,3,1,23000,17),
(2,3,2,21000,18),
(3,1,4,850000,19),
(3,1,5,120000,20),
(3,1,5,650000,21),
(4,1,4,222000,22),
(4,2,3,4000,23),
(4,2,2,1200,24),
(1,2,1,750,25),
(1,2,5,2500,26),
(1,3,2,65000,27),
(2,3,4,10000,28),
(2,3,1,15600,29),
(2,3,3,21500,30),
(3,1,3,500000,31),
(3,2,2,3100,32),
(3,3,5,31000,33),
(4,1,4,657000,34),
(4,2,1,500,35),
(4,3,1,15000,36);

INSERT INTO `user` (user_name,email,password,roles,active) VALUES
("admin","admin@admin.com","$2a$10$1HAxsgoqtXCVASxpXGcnheACN8.SbB8iQZ5o4sktAOPQEE/k2B9Ue","ROLE_ADMIN",true)