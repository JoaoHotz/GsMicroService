INSERT INTO tb_satelite (nome, agencia, tipo, ALTITUDE_KM, data_lancamento, STATUS_SAT) VALUES ('GOES-16', 'NOAA', 'Meteorológico', 35786.0, '2016-11-19', 'ATIVO');

INSERT INTO tb_satelite (nome, agencia, tipo, ALTITUDE_KM, data_lancamento, STATUS_SAT)VALUES ('Landsat-9', 'NASA', 'Óptico', 705.0, '2016-01-17', 'EM_MANUTENCAO');

INSERT INTO tb_satelite (nome, agencia, tipo, ALTITUDE_KM, data_lancamento, STATUS_SAT) VALUES ('Jason-3', 'CNES', 'Altimétrico', 1336.0, '2016-01-17', 'EM_MANUTENCAO');

INSERT INTO tb_satelite (nome, agencia, tipo, ALTITUDE_KM, data_lancamento, STATUS_SAT) VALUES ('Aqua', 'NASA', 'Meteorológico', 705.0, '2002-05-04', 'INATIVO');

INSERT INTO tb_leitura_coleta (TIPO_MEDICAO, valor_medido, unidade, data_leitura, latitude, longitude, satelite_Id) VALUES ('TEMPERATURA', 38.5, '°C', '2025-06-01', -3.4653, -62.2159, 1);
--
INSERT INTO tb_leitura_coleta (tipo_medicao, valor_medido, unidade, data_leitura, latitude, longitude, satelite_id) VALUES ('NDVI', 0.32, 'índice', '2025-06-03', -8.0476, -34.877, 2);

INSERT INTO tb_leitura_coleta (tipo_medicao, valor_medido, unidade, data_leitura, latitude, longitude, satelite_id) VALUES ('UMIDADE', 78.2, '%', '2025-06-05', 13.5137, 2.1098, 1);

INSERT INTO tb_leitura_coleta (tipo_medicao, valor_medido, unidade, data_leitura, latitude, longitude, satelite_id) VALUES ('PRESSAO', 1013.25, 'hPa', '2025-06-07', 35.2271, -80.8431, 2);