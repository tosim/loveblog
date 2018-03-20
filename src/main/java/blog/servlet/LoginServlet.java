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
 * Created by Administrator on 2017/5/18 0018.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends AutowiredHttpServlet{
    @Autowired
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        JSONObject json = new JSONObject();
        if(userService.checkUser(account,password)){
            User user = userService.getUser(account);
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            json.put("success",1);
            json.put("user",new JSONObject(user));
        }else{
            json.put("success",0);
        }
        System.out.println(json.toString());
        responseJSON(resp,json.toString());
    }
}
