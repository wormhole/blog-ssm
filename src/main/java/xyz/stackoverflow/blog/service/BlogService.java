package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Blog;

public interface BlogService {
    Blog insertBlog(Blog blog);
    Blog getBlogById(String id);
}
