package blog.servlet;

import blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@WebServlet("/AddLikeByIdServlet")
public class AddLikeByIdServlet extends AutowiredHttpServlet{
    @Autowired
    ArticleService articleService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int art_id = Integer.parseInt(req.getParameter("art_id"));
        articleService.addLikeById(art_id);
        responseJSON(resp,"{\"success\":1}");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
