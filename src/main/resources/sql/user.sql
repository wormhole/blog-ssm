create table blog.user
(
  id         char(36)     not null
    primary key,
  email      varchar(50)  not null,
  password   varchar(128) not null,
  nickname   varchar(50)  not null,
  salt       varchar(50)  not null,
  headurl    varchar(100) not null,
  signature  varchar(50)  not null,
  deleteable int          not null,
  constraint user_email_uindex
  unique (email),
  constraint user_nickname_uindex
  unique (nickname)
);


