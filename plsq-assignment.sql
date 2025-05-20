use hexaware_fsd;

create table book (
    id int primary key auto_increment,
    title varchar(255) not null,
    price decimal(10, 2) not null,
    author varchar(255) not null,
    publication_house enum('mcgraw hill', 'dreamfolks', 'warner bros') not null,
    category enum('fiction', 'war', 'comedy', 'sports') not null,
    book_count int not null,
    availability_status enum('in stock', 'out_of_stock') not null
);

insert into book (title, price, author, publication_house, category, book_count, availability_status) values
('the final empire', 499.99, 'brandon sanderson', 'mcgraw hill', 'fiction', 12, 'in stock'),
('band of brothers', 399.00, 'stephen e. ambrose', 'dreamfolks', 'war', 0, 'out_of_stock'),
('catch-22', 350.50, 'joseph heller', 'warner bros', 'comedy', 5, 'in stock'),
('open: an autobiography', 599.00, 'andre agassi', 'mcgraw hill', 'sports', 3, 'in stock'),
('the art of war', 299.00, 'sun tzu', 'dreamfolks', 'war', 8, 'in stock'),
('the hitchhiker''s guide to the galaxy', 450.75, 'douglas adams', 'warner bros', 'comedy', 0, 'out_of_stock'),
('harry potter and the sorcerer''s stone', 550.00, 'j.k. rowling', 'mcgraw hill', 'fiction', 7, 'in stock'),
('moneyball', 420.25, 'michael lewis', 'dreamfolks', 'sports', 4, 'in stock'),
('war and peace', 650.00, 'leo tolstoy', 'warner bros', 'war', 2, 'in stock'),
('the comedy of errors', 310.00, 'william shakespeare', 'mcgraw hill', 'comedy', 0, 'out_of_stock');

-- 1. fetch all books that are "in stock" and price is less than given value

delimiter $$

create procedure books_in_stock_below(in max_price decimal(10,2))
begin
    select * from book 
    where availability_status = 'in stock' and price < max_price;
end$$

delimiter ;

call books_in_stock_below(500);

-- 2. delete books that are from given publication_house 

delimiter $$

create procedure delete_books_by_pbhouse(in pb_house varchar(255))
begin
    declare bid int;

    label: loop
        select id into bid from book where publication_house = pb_house limit 1;

        if bid is null then
            leave label;
        end if;

        delete from book where id = bid;
    end loop label;
end$$

delimiter ;

call delete_books_by_pbhouse('dreamfolks');
drop procedure delete_books_by_pbhouse;

select * from book;

-- 3. update the price of books by given percent based on given category (loop-based)
delimiter $$

create procedure update_book_price(in cat varchar(255), in percentage int)
begin
    update book
    set price = round(price + (price * percentage / 100), 2)
    where id in (
        select id from (
            select id from book where category = cat
        ) as temp_ids
    );
end$$

delimiter ;

call update_book_price('comedy', 10);
drop procedure update_book_price;

select * from book;
