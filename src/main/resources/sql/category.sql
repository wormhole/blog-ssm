create table blog.category
(
  id           char(36)    not null
    primary key,
  categoryname varchar(20) not null,
  categorycode varchar(20) not null,
  deleteable   int         not null,
  constraint category_category_uindex
  unique (categoryname),
  constraint category_categorycode_uindex
  unique (categorycode)
);


