package blog.dao;

import blog.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
public class TestUserDao {
//    @Autowired
//    UserDao userDao;

//    @Test
//    public void testCheckUser(){
//
//    }
////    @Test
//    public void testGetUser(){
//        User user = userDao.selectUser("1586817454");
//        if(null != user){
//            System.out.println(user.getU_id());
//            System.out.println(user.getU_name());
//        }
//    }
//
////    @Test
//    public void testAdduser(){
//        User user = new User();
//        user.setU_tele("987654321");
//        user.setU_email("test@qq.com");
//        user.setU_name("ssss");
//        user.setU_pass("123456");
//        user.setU_img("img/defaultIcon.jpg");
//        userDao.addUser(user);
//    }
}
