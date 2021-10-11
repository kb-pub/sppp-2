drop table if exists books cascade;
drop table if exists authors cascade;
drop table if exists books_authors;

create table books (
    id bigserial primary key,
    title text unique not null,
    publish_year int check (publish_year > 0)
);

create table authors (
    id bigserial primary key,
    name text unique not null
);

create table books_authors (
    book_id bigint not null references books,
    author_id bigint not null references authors,
    primary key (book_id, author_id)
);