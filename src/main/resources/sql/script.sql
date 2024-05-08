DROP DATABASE IF EXISTS station_manager;
CREATE DATABASE station_manager;

\c  station_manager;

CREATE TABLE IF NOT EXISTS stations
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(150) NOT NULL,
    address TEXT,
    max_volume_gasoline DECIMAL(10, 2) NOT NULL,
    max_volume_diesel DECIMAL(10, 2) NOT NULL,
    max_volume_petrol DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    quantity DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS transactions
(
    id  SERIAL PRIMARY KEY,
    station_id  INT REFERENCES stations (id),
    product_id  INT REFERENCES products (id),
    type   VARCHAR(50) NOT NULL,
    quantity DECIMAL(10, 2),
    amount DECIMAL(10, 2) NOT NULL,
    date_transaction TIMESTAMP   NOT NULL
);

SELECT t.date_transaction,
       SUM(CASE WHEN p.name = 'DIESEL' AND t.type = 'ENTRY' THEN t.quantity ELSE 0 END) AS quantity_added_essence,
       SUM(CASE WHEN p.name = 'GASOLINE' AND t.type = 'ENTRY'  THEN t.quantity ELSE 0 END) AS quantity_added_gasoil,
       SUM(CASE WHEN p.name = 'PETROL' AND t.type = 'ENTRY' THEN t.quantity ELSE 0 END) AS quantity_added_petrole,
       SUM(CASE WHEN p.name = 'DIESEL' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_essence,
       SUM(CASE WHEN p.name = 'GASOLINE' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_gasoil,
       SUM(CASE WHEN p.name = 'PETROL' AND t.type = 'SORTIE' THEN t.quantity ELSE 0 END) AS quantity_sold_petrole,
       SUM(CASE WHEN p.name = 'DIESEL' THEN p.quantity ELSE 0 END) AS remaining_quantity_essence,
       SUM(CASE WHEN p.name = 'GASOLINE' THEN p.quantity ELSE 0 END) AS remaining_quantity_gasoil,
       SUM(CASE WHEN p.name = 'PETROL' THEN p.quantity ELSE 0 END) AS remaining_quantity_petrole
FROM products AS p
         INNER JOIN transactions AS t ON p.id = t.product_id
GROUP BY t.date_transaction;
