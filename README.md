# bankApp

Maven simple console bank application for my studies with support JDBC database on the Glasfish server. 
I implemented logback, slf4j and jUnit libraries. 

!jUnit tests in progress

To create database:
+ Database name: bankDB
+ User: Zawadzki
+ Password: password;

(You can create your own database but then you have to change ConnectionFactory class)

script to create tables:

create table zawadzki.customer( 
id integer primary key GENERATED BY DEFAULT AS IDENTITY,
firstName varchar(50), 
lastName varchar(50), 
address varchar(50),
pesel varchar(11) unique, 
email varchar(50) ); 

create table zawadzki.account ( 
id integer primary key GENERATED BY DEFAULT AS IDENTITY, 
notes varchar(255), 
balance double, 
id_customer integer constraint fk_customer references zawadzki.customer on delete cascade);
