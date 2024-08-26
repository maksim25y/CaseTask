create table files(
    id bigserial primary key,
    title varchar,
    description varchar,
    created_date timestamp,
    location bytea
);