package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Blog;

import java.util.List;

@Repository
public interface BlogDao {
    int insertBlog(Blog blog);

    Blog getBlogById(String id);

    Blog getBlogByCode(String blogCode);

    List<Blog> getAllBlog();

    Blog updateBlog(Blog blog);

    Blog deleteBlogById(String id);
}
