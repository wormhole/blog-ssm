package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Blog;

public interface BlogService {
    Blog saveBlog(Blog blog);
    Blog getBlogById(String id);
}
