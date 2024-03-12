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

select * from doctor;