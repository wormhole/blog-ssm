create table blog.setting
(
  id    char(36)      not null
    primary key,
  `key` varchar(100)  not null,
  value varchar(100)  not null
);