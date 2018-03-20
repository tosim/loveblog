package blog.servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@WebServlet("/LoginStateServlet")
public class LoginStateServlet extends AutowiredHttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        JSONObject json = new JSONObject();
        if(session.getAttribute("user") != null){
            json.put("success",1);
            JSONObject juser = new JSONObject(session.getAttribute("user"));
            json.put("user",juser);
            responseJSON(resp,json.toString());
        }else{
            json.put("success",0);
            responseJSON(resp,json.toString());
        }
    }
}
