create table blog.visit
(
  id     char(36)     not null
    primary key,
  url    varchar(100) not null,
  status int          not null,
  ip     varchar(50)  not null,
  agent  varchar(200) not null,
  date   datetime     not null
);