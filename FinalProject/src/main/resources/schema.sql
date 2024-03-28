CREATE SCHEMA IF NOT EXISTS appointments;

INSERT INTO doctor(
    phone_number,
    first_name,
    last_name,
    patronymic,
    speciality,
    has_removed
)
VALUES
    (89932912344, 'Зинаида', 'Петрова', 'Эдуардовна', 0, false),
    (89932943214, 'Анатолий', 'Шнипельсон', 'Эдуардович', 4, false),
    (89911112834, 'Ольга', 'Бердникова', 'Владимировна', 1, false),
    (89911344834, 'Карл', 'Юнг', 'Густав', 3, false),
    (89653437634, 'Василий', 'Сидоров', 'Петрович', 2, false);

INSERT INTO patient(
    first_name,
    last_name,
    patronymic,
    birthdate,
    phone_number,
    permanent_address,
    residential_address,
    has_removed
) VALUES
      ('Семен', 'Семенов', 'Семенович', '1918-11-11', 4321, 'Рязань', 'Рязань', false),
      ('Галина', 'Галинов', 'Эдуардовна', '1918-11-11', 645, 'Таганрог', 'Таганрог', false),
      ('Боб', 'Бобов', 'Борисович', '1918-11-11', 867, 'Воронеж', 'Воронеж', false),
      ('Фекла', 'Иванова', 'Сидоровна', '1918-11-11', 563, 'Екатеринбург', 'Екатеринбург', false),
      ('Эдуард', 'Петров', 'Евгеньевич', '1918-11-11', 1212, 'Москва', 'Москва', false);

select * from doctor;