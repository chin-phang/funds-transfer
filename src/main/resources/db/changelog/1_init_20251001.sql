-- liquibase formatted sql

-- changeset aaron:1

create table app_user (
    id bigserial,
    username varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    user_status varchar(20) not null,
    family_name varchar(100) default null,
    given_name varchar(200),
    created_by varchar(100) not null,
    created_at timestamptz not null default current_timestamp,
    last_modified_by varchar(100) default null,
    last_modified_at timestamptz default null,
    primary key (id),
    unique (username),
    unique (email)
);

create table app_role (
    id bigserial,
    name varchar(100) not null,
    primary key (id),
    unique (name)
);

create table user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references app_user (id),
    foreign key (role_id) references app_role (id)
);

insert into app_role (id, name)
values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CUSTOMER');

insert into app_user (id, username, email, password, user_status, family_name, given_name, created_by, created_at)
values
(1, 'admin', 'admin@localhost.com', '$2a$10$wpGDV8W4SaPsJIaTPTECi.2ZEVukSLkuiGT0jFdXo/AWnLbnPciaC', 'ACTIVE', null, null, 'system', current_timestamp),
(2, 'customer1', 'customer1@example.com', '$2a$10$Z1kHlXnd2hchB.VWdqIoO.j1BbIpw/b.6/3hTAfNduW7Q8PxFgCHC', 'ACTIVE', 'Customer' , 'One', 'system', current_timestamp),
(3, 'customer2', 'customer2@example.com', '$2a$10$tsjcEwViVCzIcB/ZaFGRyOKEVoQvggY0NTdoGtcERsoakIWvBw8Cq', 'ACTIVE', 'Customer' , 'Two', 'system', current_timestamp);

insert into user_role (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 2);

create table account (
    account_no varchar(20) not null,
    user_id bigint not null,
    account_type varchar(20) not null,
    account_status varchar(20) not null,
    currency varchar(3) not null,
    account_balance bigint not null default 0,
    created_by varchar(100) not null,
    created_at timestamptz not null default current_timestamp,
    last_modified_by varchar(100) default null,
    last_modified_at timestamptz default null,
    primary key (account_no),
    foreign key (user_id) references app_user (id)
);

insert into account (account_no, user_id, account_type, account_status, currency, account_balance, created_by, created_at)
values
('159786353982', 2, 'SAVINGS', 'ACTIVE', 'IDR', 420000000, 'system', current_timestamp),
('145698756324', 2, 'CURRENT', 'ACTIVE', 'USD', 2500000, 'system', current_timestamp),
('198745635478', 3, 'SAVINGS', 'ACTIVE', 'IDR', 210000000, 'system', current_timestamp),
('176549876354', 3, 'CURRENT', 'ACTIVE', 'USD', 1250000, 'system', current_timestamp);

create table transfer (
    ref_no varchar(20) not null,
    transfer_date timestamptz not null,
    transfer_type varchar(20) not null,
    from_account varchar(20) not null,
    to_account varchar(20) not null,
    currency varchar(3) not null,
    transfer_amount bigint not null,
    transfer_status varchar(20) not null,
    description text not null,
    created_by varchar(100) not null,
    created_at timestamptz not null default current_timestamp,
    last_modified_by varchar(100) default null,
    last_modified_at timestamptz default null,
    primary key (ref_no)
);