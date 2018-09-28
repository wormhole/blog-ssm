package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.BlogDao;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.util.IdGenerator;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'blog:'+#result.id")
    public Blog insertBlog(Blog blog) {
        blog.setId(IdGenerator.getId());
        dao.insertBlog(blog);
        return dao.getBlogById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'blog:'+#id", unless = "#result == null")
    public Blog getBlogById(String id) {
        return dao.getBlogById(id);
    }


}
