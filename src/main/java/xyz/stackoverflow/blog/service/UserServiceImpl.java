package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.UserDao;
import xyz.stackoverflow.blog.pojo.entity.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    @Override
    @Transactional
    public int addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    @Transactional
    public int updateHeadurl(User user) {
        return dao.updateHeadurl(user);
    }

    @Override
    @Transactional
    public int updatePassword(User user) {
        return dao.updatePassword(user);
    }

    @Override
    @Transactional
    public int udpateNickname(User user) {
        return dao.updateNickname(user);
    }
}