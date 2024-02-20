-- Чтение
-- Чтение всех строк из таблицы Товары ()
SELECT * FROM products;

-- Чтение всех строк из таблицы Покупатели ()
SELECT * FROM customers;

-- Чтение всех строк из таблицы Заказы ()
SELECT * FROM order_basket;

-- Чтение всех продуктов, дороже 1000
SELECT * FROM products
     WHERE coast::NUMERIC::INT > 1000;

-- Чтение количества покупателей
SELECT count(*) FROM customers;

-- Чтение заказов из корзины, сделанных ранее сегодняшнего дня
SELECT * FROM order_basket
    WHERE order_date < now();

-- Четние продуктов из корзины, сортированных по дате
SELECT * FROM order_basket ORDER BY order_date ASC;

-- Чтение уникальных товаров, заказанных пользователем с id = 5
SELECT DISTINCT
    description,
    coast,
    amount
FROM order_basket o
    JOIN products p
        ON o.product_id = p.id
WHERE o.customer_id = 5;

-- Чтение уникальных пользователей, у которых в корзине больше 5 заказов
SELECT DISTINCT
    first_name
FROM order_basket o
JOIN customers c
ON o.customer_id = c.id
WHERE products_amount > 5;

-- Изменение
-- Изменение фамилии у всех пользователей с именем Дмитрий
UPDATE customers
SET last_name = 'Лжедмитрий'
WHERE first_name = 'Дмитрий'
RETURNING *;

-- Скидка 20% на все товары дороже 1000
UPDATE products
SET coast = coast * 0.8
WHERE coast > 1000;

-- Удаление
-- Удаление пользователя по id
WITH del_order_basket AS (
    DELETE FROM order_basket o
        WHERE o.customer_id = 9
        RETURNING id, customer_id
)
DELETE FROM customers c
    USING del_order_basket d
WHERE c.id = d.customer_id;

-- Удаление из корзины заказов всех записей которым соответсвуют пользователи в таблице пользователей
DELETE FROM order_basket o
USING customers c
WHERE c.id = o.customer_id
RETURNING *;

-- Очистка всех таблиц с сбросом счетчика id
TRUNCATE TABLE products, customers, order_basket RESTART IDENTITY CASCADE;
