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
--        estimate_id         bigint,
        platform_id         bigint,
        foreign key (contractor_id) references users(user_id),
        foreign key (customer_id) references users(user_id),
        foreign key (status_id) references status(status_id),
--        foreign key (estimate_id) references estimates(estimate_id),
        foreign key (platform_id) references platforms (platform_id)
);

create table platform_tenders (
        platform_id         bigint not null,
        tender_id           bigint not null,
        primary key (platform_id, tender_id),
        foreign key (platform_id) references platforms(platform_id),
        foreign key (tender_id) references tenders (tender_id)
);


-- table_tender_ok
--        tender_id
--        contractor_id
--

insert into status (title) values
('черновик'),
('объявлен тендер'),
('тендер завершен'),
('в работе'),
('ждет подтверждения');

insert into tenders (title, customer_id, price, status_id, address) values
('Строительство коттеджа 600м2', 1, '20000000', 2, 'Московская область, Новорижское шоссе, снт Итренок'),
('Строительство коттеджа 200м2', 2, '1000000', 4, 'Московская область, Киевское шоссе'),
('Ремонт квартиры 250м2', 3, '1200000', 2, 'Москва, Малая ордынка, 21'),
('Дизайнерский ремонт квартиры 350м2', 3, '2500000', 3, 'Москва, ул Минская 13');


insert into platforms (title) values
('Строительство домов, коттеджей'),
('Ремонт квартир'),
('Ремонт офисов');
--
insert into platform_tenders (platform_id, tender_id) values
(1, 1),
(1, 2),
(2, 3),
(2, 4);
