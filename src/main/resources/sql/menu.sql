create table blog.menu
(
  id         char(36)    not null
    primary key,
  name       varchar(10) not null,
  url        varchar(50) not null,
  deleteable int         not null
);