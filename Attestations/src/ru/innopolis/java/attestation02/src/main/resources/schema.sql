-- Создание схемы Магазин
CREATE SCHEMA IF NOT EXISTS shop;

-- Создание таблицы Товары
CREATE TABLE IF NOT EXISTS products
(
    -- id
    id SERIAL PRIMARY KEY NOT NULL,
    -- описание
    description CHARACTER VARYING (250) NOT NULL,
    -- стоимость
    coast money,
    -- количество
    amount BIGINT
);

-- Описание таблицы Товары
COMMENT ON TABLE products IS 'Products data table';
COMMENT ON COLUMN products.id IS 'Product unique identifier';
COMMENT ON COLUMN products.description IS 'Product description';
COMMENT ON COLUMN products.coast IS 'Product coast';
COMMENT ON COLUMN products.amount IS 'Product amount';

-- Заполнение таблицы Товары (products) данными
INSERT INTO products(
    description,
    coast,
    amount
)
VALUES
    ('Кефир', 89, 45),
    ('Молоко', 54, 100),
    ('Творог', 60, 60),
    ('Хлеб', 53, 150),
    ('Аааавтомобиль', 100500, 11),
    ('Картошка', 19, 35000),
    ('Морковь', 23, 45000),
    ('Хрен', 120, 350),
    ('Редька', 23, 450),
    ('Петрушка', 50, 58);

-- Создание таблицы Покупатели
CREATE TABLE IF NOT EXISTS customers
(
    -- id
    id SERIAL PRIMARY KEY NOT NULL,
    -- имя/фамилия
    first_name CHARACTER VARYING (20) NOT NULL,
    last_name CHARACTER VARYING (20),
    FOREIGN KEY (id) references order_basket(customer_id) ON DELETE CASCADE
);

-- Описание таблицы Покупатели
COMMENT ON TABLE customers IS 'Customers data table';
COMMENT ON COLUMN customers.id IS 'Customer unique identifier';
COMMENT ON COLUMN customers.first_name IS 'Customer first name';
COMMENT ON COLUMN customers.last_name IS 'Customer last name';

-- Заполнение таблицы Покупатели (customers) данными
INSERT INTO customers(
    first_name,
    last_name
)
VALUES
    ('Зинаида', 'Петрова'),
    ('Галина', 'Иванова'),
    ('Прасковья', 'Сидорова'),
    ('Леонид', 'ДиКаприо'),
    ('Федор', 'Достоевский'),
    ('Борис', 'Борисов'),
    ('Афанасий', 'Первый'),
    ('Геннадий', 'Второй'),
    ('Жорж', 'Третий'),
    ('Дмитрий', 'Четвертый');

-- Создание таблицы Заказы
CREATE TABLE IF NOT EXISTS order_basket
(
    -- id
    id SERIAL PRIMARY KEY NOT NULL,
    -- id-товара (внешний ключ)
    product_id SERIAL REFERENCES products(id) ON DELETE CASCADE,
    -- id-заказчика (внешний ключ)
    customer_id SERIAL REFERENCES customers(id) ON DELETE CASCADE,
    -- дата заказа
    order_date DATE DEFAULT now(),
    -- количество товаров
    products_amount BIGINT
);

-- Описание таблицы Заказы
COMMENT ON TABLE order_basket IS 'Orders basket data table';
COMMENT ON COLUMN order_basket.id IS 'Basket unique identifier';
COMMENT ON COLUMN order_basket.product_id IS 'Referenced product unique identifier';
COMMENT ON COLUMN order_basket.customer_id IS 'Referenced customer unique identifier';
COMMENT ON COLUMN order_basket.order_date IS 'Order date';
COMMENT ON COLUMN order_basket.products_amount IS 'Products amount';

-- Заполнение таблицы Заказы (order_basket) данными
INSERT INTO order_basket(
     product_id,
     customer_id,
     order_date,
     products_amount
)
VALUES
    (1, 2, '2010-06-30', 3),
    (2, 3, '2011-07-12', 4),
    (3, 4, '2012-08-13', 5),
    (4, 5, '2013-09-14', 6),
    (5, 6, '2014-10-15', 7),
    (6, 7, '2015-11-16', 8),
    (7, 8, '2016-12-17', 9),
    (8, 9, '2017-06-18', 10),
    (9, 10, '2018-06-19', 1),
    (10, 1, '2019-06-20', 2);
