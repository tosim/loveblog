package blog.servlet;

import blog.entity.Article;
import blog.service.ArticleService;
//import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@WebServlet("/GetOneByIdServlet")
public class GetOneByIdServlet extends AutowiredHttpServlet{
    @Autowired
    ArticleService articleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int art_id = Integer.parseInt(req.getParameter("art_id"));
        articleService.addViewById(art_id);
        Article pre = articleService.getPreOne(art_id);
        Article next = articleService.getNextOne(art_id);
        Article article = articleService.getArticleById(art_id);
        JSONObject json = new JSONObject();
        if(pre != null){
            JSONObject jpre = new JSONObject(pre);
            json.put("pre",jpre);
        }
        if(next != null){
            JSONObject jnext = new JSONObject(next);
            json.put("next",jnext);
        }
        JSONObject jarticle = new JSONObject(article);
        json.put("article",jarticle);
//        System.out.println(json.toString());
        responseJSON(resp,json.toString());
    }
}
