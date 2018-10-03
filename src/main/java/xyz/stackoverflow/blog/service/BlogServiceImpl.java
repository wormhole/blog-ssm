package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.dao.BlogDao;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'blog:'+#result.id")
    public Blog insertBlog(Blog blog) {
        blog.setId(IdGenerator.getId());
        blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
        dao.insertBlog(blog);
        return dao.getBlogById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'blog:'+#id", unless = "#result == null")
    public Blog getBlogById(String id) {
        return dao.getBlogById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Blog> getAllBlog() {
        return dao.getAllBlog();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'blog:'+#result.id")
    public Blog updateBlog(Blog blog) {
        dao.updateBlog(blog);
        return dao.getBlogById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'blog:'+#id")
    public Blog deleteBlogById(String id) {
        Blog blog = dao.getBlogById(id);
        dao.deleteBlogById(id);
        return blog;
    }

}
