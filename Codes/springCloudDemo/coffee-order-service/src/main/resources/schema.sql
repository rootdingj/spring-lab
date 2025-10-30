drop table t_user if exists;

create table t_user (
    id bigint auto_increment,
    name varchar(255),
    age int,
    balance bigint,
    create_time timestamp,
    update_time timestamp,
    primary key (id)
);

drop table t_coffee if exists;
create table t_coffee (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    name varchar(255),
    price bigint,
    primary key (id)
);

drop table t_order if exists;
create table t_order (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    customer varchar(255),
    state integer not null,
    primary key (id)
);

drop table t_order_coffee if exists;
create table t_order_coffee (
    coffee_order_id bigint not null,
    items_id bigint not null
);
