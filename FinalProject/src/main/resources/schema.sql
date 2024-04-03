CREATE SCHEMA IF NOT EXISTS public;

INSERT INTO doctor(
    has_removed, speciality, phone_number, first_name, last_name, patronymic
)
VALUES
    (false, 0, 89932912344, 'Зинаида', 'ПетрОва', 'Эдуардовна'),
    (false, 1, 89932943214, 'Анатолий', 'Шнипельсон', 'Эдуардович'),
    (false, 2, 89911112834, 'Ольга', 'Бердникова', 'Владимировна'),
    (false, 3, 89911344834, 'Карл', 'Юнг', 'Густав'),
    (false, 4, 89653437634, 'Василий', 'Сидоров', 'Петрович');

INSERT INTO patient(
      birthdate, has_removed, phone_number, first_name, last_name, patronymic, permanent_address, residential_address
)
VALUES
    ('1918-11-11', false, 465321, 'Семен', 'Семенов', 'Семенович', 'Рязань', 'Рязань'),
    ('1918-11-11', false, 131231, 'Галина', 'Галинов', 'Эдуардовна', 'Таганрог', 'Таганрог'),
    ('1918-11-11', false, 558786, 'Боб', 'Бобов', 'Борисович', 'Воронеж', 'Воронеж'),
    ('1918-11-11', false, 908878, 'Фекла', 'Иванова', 'Сидоровна', 'Екатеринбург', 'Екатеринбург'),
    ('1918-11-11', false, 402859, 'Эдуард', 'Петров', 'Евгеньевич', 'Москва', 'Москва');

-- select * from doctor;

-- Очистка всех таблиц с сбросом счетчика id
-- TRUNCATE TABLE doctor, patient, appointment, time_slot RESTART IDENTITY CASCADE;
