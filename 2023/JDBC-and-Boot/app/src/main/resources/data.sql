insert into books (title, publish_year) values ('Book 1', 2015);
insert into books (title, publish_year) values ('Book 2', 2016);
insert into books (title, publish_year) values ('Книга 3', 2017);

insert into authors (name) values ('Author 1');
insert into authors (name) values ('Author 2');
insert into authors (name) values ('Author 3');
insert into authors (name) values ('Author 4');

insert into books_authors (book_id, author_id) values (1, 1);
insert into books_authors (book_id, author_id) values (2, 2);
insert into books_authors (book_id, author_id) values (2, 3);
insert into books_authors (book_id, author_id) values (3, 3);
insert into books_authors (book_id, author_id) values (3, 4);