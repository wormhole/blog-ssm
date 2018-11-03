create table blog.permission
(
  id             char(36)    not null
    primary key,
  permissionname varchar(20) not null,
  permissioncode varchar(20) not null,
  constraint permission_permissionname_uindex
  unique (permissionname),
  constraint permission_permissioncode_uindex
  unique (permissioncode)
);


