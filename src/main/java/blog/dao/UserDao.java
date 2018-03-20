package blog.dao;

import blog.entity.User;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface UserDao {
    public boolean checkUser(String account,String u_pass);
    public User selectUser(String account);
    public boolean addUser(User user);
}
