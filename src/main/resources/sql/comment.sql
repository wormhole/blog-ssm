create table  blog.comment
(
  id        char(36)                                    not null
    primary key,
  nickname  varchar(50)                                 not null,
  email     varchar(50)                                 not null,
  website   varchar(50)                                 null,
  content   text                                        not null,
  articleid char(36)                                    not null,
  date      timestamp   default '0000-00-00 00:00:00'   not null,
  replyto   varchar(50)                                 null,
  review    int default '0'                             not null
);


