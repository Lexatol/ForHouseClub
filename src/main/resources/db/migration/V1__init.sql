--//таблица: специализация компании
create table specializations
(
    specialization_id       serial primary key,
    specialization_title    varchar(255)
);

--//таблица пользователей сервиса
create table users
(
    user_id                 bigserial primary key,
    user_name               varchar(30) not null,
    user_password           varchar(80) not null,
    user_email              varchar(50),
    user_phone              varchar(30),
    user_photo              varchar(255)
);

--// таблица ролей пользователей:
--// заказчик, подрядчик, администратор FHC(for house club), менеджер FHC
create table roles
(
    role_id                 serial primary key,
    role_name               varchar(50) not null
);

--//кросс таблица пользователей, ролей
create table users_roles
(
    user_id                bigint not null,
    role_id                int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (user_id),
    foreign key (role_id) references roles (role_id)
);

--//таблица личных кабинетов пользователей
create table profile_users (
    profile_id             bigserial primary key,
    user_id                bigint not null,
    user_position          varchar(255),
    foreign key (user_id) references users (user_id)
);


--//таблица компаний
create table companies
(
    company_id             bigserial primary key,
    company_name           varchar(255) not null,
    inn                    int not null,
    kpp                    int not null,
    general_manager        bigint       not null,
    project_manager        bigint       not null,
    legal_address          varchar(255),
    actual_address         varchar(255),
    company_phone          varchar(255),
    company_email          varchar(255),
    composition_And_Number int,
    description            varchar,
    foreign key (general_manager) references users (user_id),
    foreign key (project_manager) references users (user_id)
);


--//таблица профилей компаний
create table profile_companies
(
    profile_id             bigserial primary key,
    company_id             bigint,
    specialization_id      int not null,
    foreign key (specialization_id) references specializations (specialization_id),
    foreign key (company_id) references companies (company_id)
);

--//таблица связей компаний
create table connection_contractor_customers
(
    contractor_id          bigint,
    company_customer_id    bigint,
    status_connection      varchar(255), --//статус: подрядчик, поставщик
    primary key (contractor_id, company_customer_id),
    foreign key (contractor_id) references companies (company_id),
    foreign key (company_customer_id) references companies (company_id),
    foreign key (status_connection) references roles (role_id)
);

create table connection_contractor_providers
(
    contractor_id          bigint,
    company_provider_id    bigint,
    status_connection      varchar(255), --//статус: подрядчик, поставщик
    primary key (contractor_id, company_provider_id),
    foreign key (contractor_id) references companies (company_id),
    foreign key (company_provider_id) references companies (company_id),
    foreign key (status_connection) references roles (role_id)
);

--//таблица связей компании и project_manager
create table companies_pm
(
    company_id             bigint not null,
    user_id                int not null,
    primary key (company_id, user_id),
    foreign key (user_id) references users (user_id),
    foreign key (company_id) references companies (company_id)
);

insert into specializations (specialization_title)
values ('Builder'),
       ('Designer'),
       ('Architect'),
       ('Engineering');

insert into users (user_name, user_password, user_email, user_phone)
values ('Petya', 123, 'petya@google.com', 89851002121),
       ('Vasya', 123, 'vasya@google.com', 89851002122),
       ('Misha', 123, 'misha@google.com', 89851002123),
       ('Lesha', 123, 'lesha@google.com', 89851002124),
       ('Ksenya', 123, 'ksenya@google.com', 89851002125),
       ('Olya', 123, 'olya@google.com', 89851002126);


insert into roles (role_name)
VALUES ('CONTRACTOR'),
       ('CUSTOMER'),
       ('PROVIDER'),
       ('ADMIN'),
       ('MANAGER');

insert into companies (company_name,inn, kpp, general_manager, project_manager,
                       legal_address, composition_And_Number)
values ('Capital Group',123445,2312,  1, 2, 'Russia, Moscow', 10000),
       ('Design Company',123445,2312,  3, 4, 'Italia, Rim', 8),
       ('Engineering',123445,2312,  5, 6, 'Russia, Tula', 100);

insert into profile_users (user_id, user_position)
values
(1, 'general manager'),
(2, 'project manage');

insert into users_roles (user_id, role_id)
values (1, 4),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 1),
       (3, 3);

insert into profile_companies (profile_id, company_id, specialization_id)
values (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);

insert into connection_contractor_customers (contractor_id, company_customer_id, status_connection)
values (1, 1, 2),
       (1, 2, 3);

insert into connection_contractor_providers (contractor_id, company_provider_id, status_connection)
values (1, 1, 2),
       (1, 2, 3);

insert into companies_pm (company_id, user_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4);


