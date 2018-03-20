package blog.servlet;

import blog.entity.User;
import blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends AutowiredHttpServlet{
    @Autowired
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String img = req.getParameter("img");

        System.out.println("收到注册请求");
        System.out.println("username = " + username);
        System.out.println("email = " + email);
        System.out.println("telephone = " + telephone);
        System.out.println("img = " + img);
        System.out.println("password = " + password);

        JSONObject json = new JSONObject();
        if((telephone == null || telephone.equals("")) && (email == null) || email.equals("")){
            json.put("success",0);
        }
        User user = new User();
        user.setU_tele(telephone);
        user.setU_email(email);
        user.setU_name(username);
        user.setU_pass(password);
        if(img == null || img.equals("")){
            user.setU_img("img/defaultIcon.jpg");
        }else{
            user.setU_img(img);
        }
        userService.addUser(user);
        json.put("success",1);
        json.put("user",new JSONObject(user));
        System.out.println(json.toString());
        HttpSession session = req.getSession(true);
        user = userService.getUser(user.getU_tele());
        session.setAttribute("user",user);
        responseJSON(resp,json.toString());
    }
}
