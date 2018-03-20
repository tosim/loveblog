package blog.servlet;

import blog.entity.Tool;
import blog.service.ToolService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@WebServlet("/GetToolByIdServlet")
public class GetToolByIdServlet extends AutowiredHttpServlet{
    @Autowired
    ToolService toolService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int t_id = Integer.parseInt(req.getParameter("t_id"));
        Tool tool = toolService.getToolById(t_id);
        JSONObject json = new JSONObject(tool);
        System.out.println(json.toString());
        responseJSON(resp,json.toString());
    }
}
