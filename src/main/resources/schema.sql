
create table employee
(
   id integer not null,
   first_name varchar(255) not null,
   middle_initial varchar(255),
   last_name varchar(255) not null,
   date_of_birth timestamp not null,
   date_of_employment timestamp not null,
   status tinyint(4) not null default '0',
   primary key(id)
);