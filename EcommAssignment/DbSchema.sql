create database ecom_assignment;

use ecom_assignment;

create table customer(
id int primary key auto_increment,
customer_name varchar(255),
address varchar(255)
);

create table category(
id int primary key auto_increment,
category_name varchar(255)
);

create table product(
id int primary key auto_increment,
prod_name varchar(255),
price double,
prod_desc varchar(255),
category_id int,
foreign key(category_id) references category(id)
);

create table purchase(
id int primary key auto_increment,
purchase_date varchar(255),
customer_id int,
product_id int,
foreign key(customer_id) references customer(id),
foreign key(product_id) references product(id)
)


select * from customer;
select * from product;
select * from category;