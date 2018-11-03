create table blog.article
(
  id          char(36)                                not null
    primary key,
  userid      char(36)                                not null,
  title       varchar(50)                             not null,
  articlemd   text                                    not null,
  articlehtml text                                    not null,
  categoryid  char(36)                                not null,
  url         varchar(50)                             not null,
  likes       int default '0'                         not null,
  hits        int default '0'                         not null,
  modifydate  datetime                                not null,
  createdate  datetime                                not null,
  constraint blog_blogcode_uindex
  unique (url)
);

