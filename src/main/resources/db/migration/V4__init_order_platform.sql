create table status (
        status_id           bigserial primary key,
        title               varchar(20)
);

create table platforms(
        platform_id         bigserial primary key,
        title               varchar(100)
);

create table tenders(
        tender_id           bigserial primary key,
        title               varchar(100) not null,
        data_start          varchar(20),
        address             varchar(100),
        description         varchar(1000),
        contractor_id       bigint,
        customer_id         bigint not null,
        price               bigint,
        status_id           bigint,
        estimate_id         bigint,
        platform_id         bigint,
        foreign key (contractor_id) references users(user_id),
        foreign key (customer_id) references users(user_id),
        foreign key (status_id) references status(status_id),
        foreign key (estimate_id) references estimates(estimate_id),
        foreign key (platform_id) references platforms (platform_id)
);

insert into status (title) values
('черновик'),
('объявлен тендер'),
('тендер завершен'),
('в работе'),
('ждет подтверждения');

insert into estimates (company_id, estimate_number, estimate_sum) values
(1, 1, 1000), (2,2, 2000), (3,3, 3000), (4,4, 4000);

insert into platforms (title) values
('Строительство домов, коттеджей'),
('Ремонт квартир'),
('Ремонт офисов');

insert into tenders (title, customer_id, contractor_id, price, status_id, address, estimate_id, platform_id) values
('Строительство коттеджа 600м2', 1, null, '20000000', 2, 'Московская область, Новорижское шоссе, снт Итренок', 1, 1),
('Строительство коттеджа 400м2', 1,2, '500000', 4, 'Московская область, Калужское шоссе', 2, 1),
('Строительство коттеджа 200м2', 2,null, '1000000', 2, 'Московская область, Киевское шоссе', 2, 1),
('Строительство коттеджа 120м2', 2,1, '100000', 4, 'Московская область, Киевское шоссе', 2, 1),
('Ремонт квартиры 250м2', 3,null, '1200000', 2, 'Москва, Малая ордынка, 19', 3, 2),
('Ремонт квартиры 140м2', 2,1, '1500000', 3, 'Москва, Старый арбат, 56', 3, 2),
('Ремонт квартиры 290м2', 1,2, '1700000', 4, 'Москва, Мосфильмовская, 35', 3, 2),
('Ремонт квартиры 340м2', 2,null, '1900000', 2, 'Москва, Краснопролетарская, 7', 3, 2),
('Ремонт квартиры 450м2', 3,1, '22100000', 3, 'Москва, Месницкая, 11', 3, 2),
('Дизайнерский ремонт квартиры 350м2', 1,null, '2500000', 3, 'Москва, ул Минская 13', 4, 2);





