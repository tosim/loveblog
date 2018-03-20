package blog.service.Impl;

import blog.dao.UserDao;
import blog.entity.User;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUser(String account) {
        return userDao.selectUser(account);
    }

    @Override
    public boolean checkUser(String account, String u_pass) {
        return userDao.checkUser(account,u_pass);
    }
}
