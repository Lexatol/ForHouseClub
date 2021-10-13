CREATE TABLE materials_categories
(
    category_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar
);

INSERT INTO materials_categories(name, description)
VALUES ('Сухие смеси', 'Сухие строительные смеси и гидроизоляция'),
       ('Пиломатериалы', 'Пиломатериалы'),
       ('Металлопрокат', 'Металлопрокат');

CREATE TABLE materials
(
    material_id bigserial PRIMARY KEY,
    name        varchar(255),
    category_id bigserial,
    description varchar
);

INSERT INTO materials(name, description, category_id)
VALUES ('Пескобетон (ЦПС) М300 Dauer 40 кг',
        'Тип товара: Пескобетон; Бренд: Dauer; Назначение: Универсальные; Марка: М300; Размер фракции: 5; Толщина слоя: 20-200 мм; Цвет: Серый; Прочность сцепления: не менее 0,5 МПа; Расход воды на 1 кг сухой смеси: 0,12-0,15 л; Марочная прочность на сжатие: Не менее 30 Мпа;',
        1),
       ('Смесь цементно-песчаная (ЦПС) М200 Dauer 50 кг', 'Смесь цементно-песчаная (ЦПС) М200 Dauer 50 кг', 1),
       ('Брусок сухой строганый 50х50х3000 мм сорт АВ хвойные породы',
        'Брусок сухой строганый 50х50х3000 мм сорт АВ хвойные породы', 2),
       ('Доска сухая строганая 20х95х3000 мм хвойные породы сорт АВ',
        'Доска сухая строганая 20х95х3000 мм хвойные породы сорт АВ', 2),
       ('Сетка кладочная 50х50 мм d2,5 мм 0,5х2 м', 'Сетка кладочная 50х50 мм d2,5 мм 0,5х2 м', 3),
       ('Уголок горячекатаный 50х50х5 мм 3 м', 'Уголок горячекатаный 50х50х5 мм 3 м', 3);

CREATE TABLE works_categories
(
    category_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar
);

INSERT INTO works_categories(name)
VALUES ('окна'),
       ('потолок'),
       ('стены');

CREATE TABLE operations_categories
(
    category_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar
);

INSERT INTO operations_categories(name, description)
VALUES ('Ручные работы', ''),
       ('Механизированные работы', ''),
       ('Уборка', '');

CREATE TABLE operations
(
    operation_id bigserial PRIMARY KEY,
    name         varchar(255),
    category_id  bigserial,
    description  varchar
);

INSERT INTO operations(name, description, category_id)
VALUES ('Постучать', '', 1),
       ('Поднять', '', 1),
       ('Закрутить', '', 2),
       ('Выкрутить', '', 2),
       ('Подмести', '', 3),
       ('Вынести', '', 3);

CREATE TABLE works_templates
(
    template_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    category_id bigserial,
    description varchar
);

INSERT INTO works_templates (name, category_id)
VALUES ('Устройство временного туалета и душевой', 1),
       ('Монтаж защитных ограждающих конструкций пола и стен', 2),
       ('Монтаж перегородок из ПГП блоков 10см (влагостойких)', 3),
       ('Монтаж перегородок из ГКЛ в два слоя (с вращающимся телевизором)', 3);

CREATE TABLE works_templates_operations
(
    row_id       bigserial PRIMARY KEY NOT NULL,
    template_id  bigserial             NOT NULL,
    operation_id bigserial             NOT NULL,
    quantity     int                   NOT NULL DEFAULT 0
);

INSERT INTO works_templates_operations (template_id, operation_id, quantity)
VALUES (1, 1, 1),
       (1, 2, 2),
       (2, 6, 4),
       (2, 5, 8),
       (3, 2, 5),
       (3, 1, 2);


CREATE TABLE works_templates_materials
(
    row_id       bigserial PRIMARY KEY NOT NULL,
    operation_id bigserial             NOT NULL,
    material_id  bigserial             NOT NULL,
    quantity     int                   NOT NULL DEFAULT 0
);

INSERT INTO works_templates_materials (operation_id, material_id, quantity)
VALUES (1, 1, 1),
       (1, 2, 2),
       (2, 3, 2),
       (2, 4, 2),
       (2, 5, 6),
       (3, 1, 2);


CREATE TABLE estimates
(
    estimate_id     bigserial PRIMARY KEY NOT NULL,
    company_id      bigserial             NOT NULL,
    estimate_number integer               NOT NULL DEFAULT 0,
    estimate_date   date                  NOT NULL DEFAULT now(),
    address         varchar(255),
    author          bigserial
);

CREATE TABLE estimates_works
(
    row_id         bigserial PRIMARY KEY NOT NULL,
    estimate_id    bigserial             NOT NULL,
    works_template bigserial             NOT NULL
);

CREATE TABLE estimates_operations
(
    row_id       bigserial PRIMARY KEY NOT NULL,
    estimate_id  bigserial             NOT NULL,
    work_id      bigserial             NOT NULL,
    operation_id bigserial             NOT NULL,
    quantity     int                   NOT NULL DEFAULT 0
);

CREATE TABLE estimates_materials
(
    row_id       bigserial PRIMARY KEY NOT NULL,
    estimate_id  bigserial             NOT NULL,
    operation_id bigserial             NOT NULL,
    material_id  bigserial             NOT NULL,
    quantity     int                   NOT NULL DEFAULT 0
);

INSERT INTO works_templates_operations (template_id, operation_id, quantity)
VALUES (1, 1, 1),
       (1, 2, 2),
       (2, 6, 4),
       (2, 5, 8),
       (3, 2, 5),
       (3, 1, 2);
