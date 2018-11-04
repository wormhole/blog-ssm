create table blog.comment
(
  id        char(36)    not null
    primary key,
  nickname  varchar(20) not null,
  email     varchar(30) not null,
  website   varchar(50) null,
  content   text        not null,
  articleid char(36)    not null,
  date      datetime    not null,
  replyto   varchar(20) null,
  review    int         not null
);

