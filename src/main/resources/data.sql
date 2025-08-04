INSERT INTO WOOD_CATEGORY (name) VALUES
                                       ( 'lancamentos'),
                                       ( 'pendentes'),
                                       ( 'abajures'),
                                       ( 'arandelas'),
                                       ( 'colunas'),
                                       ( 'lanternas');

INSERT INTO WOOD_PRODUCT ( name, sku, ean, size, img_url) VALUES
                                                                  ( 'Camiseta Azul', 1001, 7891234567890, 'M', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Tênis Esportivo', 1002, 7891234567891, '42', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Jaqueta Couro', 1003, 7891234567892, 'G', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Relógio Digital', 1004, 7891234567893, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Calça Jeans', 1005, 7891234567894, '42', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Smartphone X', 1006, 7891234567895, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Bicicleta Speed', 1007, 7891234567896, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Bolsa Feminina', 1008, 7891234567897, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Boneco Colecionável', 1009, 7891234567898, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg'),
                                                                  ( 'Sofá 3 Lugares', 1010, 7891234567899, 'Único', 'https://wood.3dluzedesign.com.br/wp-content/uploads/2022/09/lanterna-bela-p-scaled.jpg');

INSERT INTO tb_product_category (product_id, category_id) VALUES
                                                                 (1, 1), (1, 2), -- Camiseta Azul (Roupas, Masculino)
                                                                 (2, 1), (2, 4), -- Tênis Esportivo (Calçados, Esporte)
                                                                 (3, 1), (3, 5), -- Jaqueta Couro (Roupas, Casacos)
                                                                 (4, 2),        -- Relógio Digital (Acessórios)
                                                                 (5, 3),        -- Calça Jeans (Roupas)
                                                                 (6, 4),        -- Smartphone X (Eletrônicos)
                                                                 (7, 5),        -- Bicicleta Speed (Esporte)
                                                                 (8, 6),        -- Bolsa Feminina (Feminino)
                                                                 (9, 2),        -- Boneco Colecionável (Infantil)
                                                                 (10, 4);