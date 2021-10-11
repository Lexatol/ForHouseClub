create table pricelist_companies(
            pricelist_id        bigserial primary key,
            operation_id        bigint not null,
            company_id          bigint,
            price_operation     bigint not null,
            foreign key (operation_id) references operations (operation_id),
            foreign key (company_id) references companies (company_id)
);

insert into pricelist_companies (operation_id, company_id, price_operation) values
            (1, 1, 100),
            (2, 1, 200),
            (3, 1, 300),
            (1, 2, 120),
            (2, 2, 230),
            (3, 2, 340);

alter table profile_companies add pricelist_id bigint;

alter table profile_companies add foreign key (pricelist_id)
  references pricelist_companies(pricelist_id);

insert into profile_companies (profile_id, company_id, specialization_id, pricelist_id) values      (1, 1, 1, 1),
           (2, 2, 2, 2),
           (3, 3, 3, 3);

