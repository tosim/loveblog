package blog.dao.impl;

import blog.dao.UserDao;
import blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Repository("UserDao")
public class UserDaoImpl implements UserDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> userRowMapper = new BeanPropertyRowMapper<User>(User.class);
    private static final String baseColumn = " u_id,u_name,u_tele,u_email,u_pass,u_img ";

    @Override
    public User selectUser(String account) {
        String sql = "select" + baseColumn +"from user where u_tele=? or u_email=?";
        try{
            User user = jdbcTemplate.queryForObject(sql,userRowMapper,new Object[]{account,account});
            return user;
        }catch (EmptyResultDataAccessException empty){
            return null;
        }
    }

    @Override
    public boolean checkUser(String account, String u_pass) {
        String sql = "select count(*) from user where (u_tele=? or u_email=?) and u_pass=?";
        int count = jdbcTemplate.queryForObject(sql,Integer.class,new Object[]{account,account,u_pass});
        return count > 0;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "insert into user("+baseColumn+") values(?,?,?,?,?,?)";
        List list = new ArrayList();
        list.add(user.getU_id());
        list.add(user.getU_name());
        list.add(user.getU_tele());
        list.add(user.getU_email());
        list.add(user.getU_pass());
        list.add(user.getU_img());
        jdbcTemplate.update(sql,list.toArray());
        return true;
    }
}
