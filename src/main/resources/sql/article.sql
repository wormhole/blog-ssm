create table test.article
(
  id          char(36)                            not null
    primary key,
  userid      char(36)                            not null,
  title       varchar(50)                         not null,
  articlemd   text                                not null,
  articlehtml text                                not null,
  date        timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  categoryid  char(36)                            not null,
  url         varchar(50)                         not null,
  constraint blog_blogcode_uindex
  unique (url)
);


