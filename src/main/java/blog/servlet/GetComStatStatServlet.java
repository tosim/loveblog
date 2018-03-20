package blog.servlet;

import blog.service.CommentServive;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@WebServlet("/GetComStatStatServlet")
public class GetComStatStatServlet extends AutowiredHttpServlet{
    @Autowired
    CommentServive commentServive;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int art_id = Integer.parseInt(req.getParameter("art_id"));
        int totalUsers = commentServive.getTotalUser(art_id);
        int totalComments = commentServive.getTotalComments(art_id);

        JSONObject json = new JSONObject();
        json.put("success",1);
        json.put("totalUsers",totalUsers);
        json.put("totalComments",totalComments);
        System.out.println(json.toString());
        responseJSON(resp,json.toString());
    }
}
