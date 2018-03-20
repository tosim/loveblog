package blog.service;

import blog.entity.User;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface UserService {
    public boolean checkUser(String account,String u_pass);
    public User getUser(String account);
    public boolean addUser(User user);
}
