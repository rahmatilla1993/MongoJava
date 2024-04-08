create table post
(
    id          bigserial primary key,
    title       varchar not null,
    description varchar not null,
    author_id   bigint references author (id)
);