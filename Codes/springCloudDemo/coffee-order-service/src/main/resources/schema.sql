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