create table blog.role
(
  id       char(36)    not null
    primary key,
  rolename varchar(20) not null,
  rolecode varchar(20) not null,
  constraint role_rolename_uindex
  unique (rolename),
  constraint role_rolecode_uindex
  unique (rolecode)
);


