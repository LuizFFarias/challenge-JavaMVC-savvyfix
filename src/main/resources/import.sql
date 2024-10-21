insert into Produto (id_prod, nm_prod, desc_prod, marca_prod, preco_fixo, img_prod) values (625, 'Tenis Casual', 'Para ser utilizado em diversas situações', 'Kolosh', 139.99, 'https://dqk9memo83p8i.cloudfront.net/Custom/Content/Products/11/10/1110731_tenis-kolosh-calce-facil-com-ziper-preto-c1299-00001_l29_638065171521199149.jpg');
insert into Produto (id_prod, nm_prod, desc_prod, marca_prod, preco_fixo, img_prod) values (830, 'Tenis Cano Alto', 'Para ser utilizado em diversas situações', 'AllStar', 269.90, 'https://convexo.com.br/files/product/thumb/2072/all-star-plataforma-hi-preto-branco.jpg');
insert into Produto (id_prod, nm_prod, desc_prod, marca_prod, preco_fixo, img_prod) values (953, 'Tenis Esportivo', 'Para praticar esportes', 'Nike', 250.99, 'https://imgnike-a.akamaihd.net/1300x1300/025197IMA8.jpg');
insert into Produto (id_prod, nm_prod, desc_prod, marca_prod, preco_fixo, img_prod) values (182, 'Tenis Plataforma', 'Para deixar a pessoa mais alta', 'Puma', 450.99, 'https://espacotenis.vteximg.com.br/arquivos/ids/173899-480-480/puma-smash-bdp-preto.jpg?v=638314131045000000');
insert into Produto (id_prod, nm_prod, desc_prod, marca_prod, preco_fixo, img_prod) values (644, 'Tenis Chunky Sneakers', 'Para ser diferente', 'Adidas', 479.99, 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/15f901c90a9549d29104aae700d27efb_9366/Tenis_Superstar_Preto_EG4959_01_standard.jpg');

insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (811, 49065220, 'Rua Conego Serapiao Machado', 5, 'Bairro industrial', 'Aracaju', 'SE', 'Bra');
insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (734, 45208190, 'Rua acampamento velho', 10, 'Bairro Jequiezinho', 'Jequie', 'BA', 'Bra');
insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (764, 77064114, 'Rua SF', 15, 'Bairro Setor Santa Fé', 'Palmas', 'TO', 'Bra');
insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (116, 79841510, 'Rua Venezuela', 20, 'Parque das Nações', 'Dourado', 'MS', 'Bra');
insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (344, 58068183, 'Rua José Pereira de Lima Filho', 25, 'Bairro Gramame', 'Joao Pessoa', 'PB', 'BRA');
insert into Endereco (id_endereco, cep_endereco, rua_endereco, num_endereco, bairro_endereco, cidade_endereco, estado_endereco, pais) values (999, 58068183, 'Rua José Pereira de Lima Filho', 25, 'Bairro Gramame', 'Joao Pessoa', 'PB', 'BRA');

insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (877, 'Lorenzo Giovanni Calebe Ferreira', 26019118088, 'MPRFuQYLWSfN0iU', 811);
insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (136, 'Eduardo Bruno Leandro Gonçalves', 84214739051, 'L6WneERuzRiEtko', 734);
insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (144, 'Matheus Luan Fogaça', 55533248072, 'RMEzoAw3OlOgDri', 764);
insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (642, 'Pedro Henrique Márcio Costa', 28175653043, 's8histwMgcK7TLA', 116);
insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (993, 'Benedita Simone Melissa da Cunha', 42869783035, '6hYQnI0A7dBmJO8', 344);
insert into Cliente (id_cliente, nm_clie, cpf_clie, senha_clie, id_endereco) values (999, 'Luiz Fillipe Farias', 48951543862, '$2a$12$VVNhvcnwB01Th08rxFeeQe7pLGSUFAZaNPCCF39NBRmjWsBVHRWVG', 999);

insert into Atividades (id_atividades, preco_variado, horario_atual, localizacao_atual, clima_atual, qntd_procura, demanda_produto, id_cliente, id_produto) values (435, 132.58, '10:31:57.12', 'Bairro Industrial', 'Frio', 2, 'Ba', 877, 625);
insert into Atividades (id_atividades, preco_variado, horario_atual, localizacao_atual, clima_atual, qntd_procura, demanda_produto, id_cliente, id_produto) values (493, 174.60, '15:49:26.12', 'Bairro Santos Dumont', 'Calor', 3, 'Ba', 136, 830);
insert into Atividades (id_atividades, preco_variado, horario_atual, localizacao_atual, clima_atual, qntd_procura, demanda_produto, id_cliente, id_produto) values (807, 303.99, '05:43:49.12', 'Bairro Santo Meu', 'Ameno', 22, 'Al', 144, 953);
insert into Atividades (id_atividades, preco_variado, horario_atual, localizacao_atual, clima_atual, qntd_procura, demanda_produto, id_cliente, id_produto) values (932, 467.54, '08:41:53.12', 'Parque Pinheiros', 'Chuva', 10, 'Me', 642, 182);
insert into Atividades (id_atividades, preco_variado, horario_atual, localizacao_atual, clima_atual, qntd_procura, demanda_produto, id_cliente, id_produto) values (108, 458.97, '09:29:48.12', 'Jardim das Margaridas', 'Calor', 11,'Me', 993, 644);

insert into Compra (id_compra, nm_prod, qntd_prod, valor_compra, especificacao_prod, id_atividades, id_cliente, id_prod) values (862, 'Tenis Casual', 3, 397.74, 'Tamanho 37, 38 e 39', 435, 877, 625);
insert into Compra (id_compra, nm_prod, qntd_prod, valor_compra, especificacao_prod, id_atividades, id_cliente, id_prod) values (598, 'Tenis Cano Alto', 4, 698.40, 'Todos tamanho 34', 493, 136, 830);
insert into Compra (id_compra, nm_prod, qntd_prod, valor_compra, especificacao_prod, id_atividades, id_cliente, id_prod) values (187, 'Tenis Esportivo', 1, 303.99, 'Tamanho 35', 807, 144, 953);
insert into Compra (id_compra, nm_prod, qntd_prod, valor_compra, especificacao_prod, id_atividades, id_cliente, id_prod) values (216, 'Tenis Plataforma', 7, 322.78, 'Tam 34, 35, 37, 37, 38, 39, 40', 932, 642, 182);
insert into Compra (id_compra, nm_prod, qntd_prod, valor_compra, especificacao_prod, id_atividades, id_cliente, id_prod) values (560, 'Tenis Chunky Sneakers', 1, 458.97, 'Tamanho 36', 108, 993, 644);

insert into Role(id_role, nome_role) values (1, 'ROLE_USER');
insert into Role(id_role, nome_role) values (2, 'ROLE_ADMIN');

insert into Cliente_Role (id_role, id_cliente) values (1, 999);