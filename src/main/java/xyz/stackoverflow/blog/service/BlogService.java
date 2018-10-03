package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Blog;

import java.util.List;

public interface BlogService {
    Blog insertBlog(Blog blog);

    Blog getBlogById(String id);

    List<Blog> getAllBlog();

    Blog updateBlog(Blog blog);

    Blog deleteBlogById(String id);
}
