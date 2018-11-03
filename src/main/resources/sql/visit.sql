create table blog.visit
(
  id   char(36)     not null
    primary key,
  url  varchar(100) not null,
  ip   varchar(50)  not null,
  date datetime     not null
);