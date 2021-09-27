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

CREATE TABLE operations_categories
(
    category_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar
);

CREATE TABLE operations
(
    operation_id bigserial PRIMARY KEY,
    name         varchar(255),
    category_id  bigserial,
    description  varchar
);

CREATE TABLE works_templates
(
    template_id bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar
);

CREATE TABLE works_templates_materials
(
    row_id      bigserial PRIMARY KEY NOT NULL,
    template_id bigserial             NOT NULL,
    material_id bigserial             NOT NULL,
    quantity    int                   NOT NULL DEFAULT 0
);

CREATE TABLE works_templates_operations
(
    row_id       bigserial PRIMARY KEY NOT NULL,
    template_id  bigserial             NOT NULL,
    operation_id bigserial             NOT NULL,
    quantity     int                   NOT NULL DEFAULT 0
);

