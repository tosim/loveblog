package blog.servlet;

import blog.entity.Article;
import blog.service.ArticleService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
@WebServlet("/GetTenNewestServlet")
public class GetTenNewestServlet extends AutowiredHttpServlet{
    @Autowired
    ArticleService articleService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articleList = (ArrayList<Article>)articleService.getTenNewest();
//        System.out.println("size:" + articleList.size());
        JSONArray json = new JSONArray();
        for(Article art : articleList){
//            System.out.println("id:" + art.getArt_id());
//            System.out.println("title:" + art.getArt_title());
//            System.out.println("class:" + art.getArt_class());
            JSONObject article = new JSONObject(art);
            json.put(article);
        }
        //System.out.println("getTenHotest\n"+json.toString());
        responseJSON(resp,json.toString());
    }
}
