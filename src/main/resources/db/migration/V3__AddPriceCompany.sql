create table price_items(
        item_id             bigserial primary key,
        operation_id        bigint,
        price               bigint,
        foreign key (operation_id) references operations (operation_id)
);

create table prices (
        price_id            bigserial primary key,
        profile_id          bigint,
        foreign key (profile_id) references profile_companies(profile_id)
);

create table pricelist (
        price_id        bigint not null,
        item_id         bigint not null,
        primary key (price_id, item_id),
        foreign key (price_id) references prices(price_id),
        foreign key (item_id) references price_items (item_id)
);
--
alter table profile_companies add price_id bigint;

alter table profile_companies add foreign key (price_id)
  references prices(price_id);

insert into price_items (operation_id, price) values
        (1, 1000), (1, 1200), (1, 1300),
        (2, 2000), (2, 2200), (2, 2300),
        (3, 3000), (3, 3200), (3, 3300);

insert into prices (profile_id) values (1), (2), (3);

insert into pricelist (price_id, item_id) values
        (1, 1), (1, 4), (1, 7),
        (2, 2), (2, 5), (2, 8),
        (3, 3), (3, 6), (3, 9);

insert into profile_companies (company_id, specialization_id, price_id) values
        (1, 1, 1),
        (2, 2, 2),
        (3, 3, 3);
--        (1, 1, 1), (1, 4, 2), (2, 2, 3), (2, 3, 4), (3, 4, 5);

