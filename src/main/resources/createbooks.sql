create table IF NOT EXISTS books
(
    id smallint not null generated always
        as identity primary key,
    book_name   varchar(50) not null,
    genre       varchar(50) not null,
    author      varchar(50) not null,
    publication smallint    not null,
    count_pages smallint    not null,
    price       smallint    not null
);

INSERT INTO books (id, book_name, genre, author, publication, count_pages, price) VALUES
(DEFAULT, 'Архимаг', 'фэнтези', 'Роберт Энтони Сальваторе', 2014, 712, 860),
(DEFAULT, 'Крестный отец', 'драма', 'Марио Пьюзо', 1969, 852, 920),
(DEFAULT, 'Дезертир', 'фантастика', 'Алексей Степанов', 2009, 621, 755);