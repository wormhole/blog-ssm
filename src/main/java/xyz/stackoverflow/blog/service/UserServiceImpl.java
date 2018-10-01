package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.dao.UserDao;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.util.IdGenerator;
import xyz.stackoverflow.blog.util.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'user:'+#email", unless = "#result == null")
    public User getUserByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email")
    public User insertUser(User user) {
        user.setHeadurl("/static/custom/image/cam.png");
        user.setNickname(HtmlUtils.htmlEscape(user.getNickname()));
        user.setSignature("这个人很懒,没有留下任何东西");
        user.setId(IdGenerator.getId());
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        dao.insertUser(user);
        return dao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email")
    public User updateHeadUrl(User user) {
        dao.updateHeadUrl(user);
        return dao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email")
    public User updatePassword(User user) {
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        dao.updatePassword(user);
        return dao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email")
    public User updateBaseInfo(User user) {
        user.setNickname(HtmlUtils.htmlEscape(user.getNickname()));
        user.setSignature(HtmlUtils.htmlEscape(user.getSignature()));
        dao.updateBaseInfo(user);
        return dao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExist(String email) {
        if (dao.isExist(email) != 0) {
            return true;
        } else {
            return false;
        }
    }
}