package blog.servlet;

import blog.entity.Article;
import blog.service.ArticleService;
import blog.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@WebServlet("/GetTwoArticlesServlet")
public class GetTwoArticlesServlet extends AutowiredHttpServlet{
    @Autowired
    ArticleService articleService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boyArtNum = Integer.parseInt(req.getParameter("boyArtNum"));
        int girlArtNum = Integer.parseInt(req.getParameter("girlArtNum"));
        System.out.println("收到请求");
        Article boyArticle = articleService.queryOneFromNum(boyArtNum,"姚小城");
        Article girlArticle = articleService.queryOneFromNum(girlArtNum,"冯小婧");

        JSONObject json = new JSONObject();
        if(boyArticle != null){
            System.out.println("boy is not null!");
            JSONObject boy = new JSONObject(boyArticle);
            String art_abstract = StringUtil.Html2Text(boyArticle.getArt_content());
            boy.put("art_abstract",art_abstract);
            json.put("boy",boy);
        }else{
            ;//System.out.println("boy is null!");
        }
        if(girlArticle != null){
            System.out.println("girl is not null!");
            JSONObject girl = new JSONObject(girlArticle);
            String art_abstract = StringUtil.Html2Text(girlArticle.getArt_content());
            girl.put("art_abstract",art_abstract);
            json.put("girl",girl);
        }else {
            ;//System.out.println("girl is null!");
        }
        if(girlArticle != null || boyArticle != null){
            json.put("end",0);//数据库中没有数据了
        }else{
            json.put("end",1);//数据库中还有更多的数据
        }
        //System.out.println(json.toString());
        responseJSON(resp,json.toString());
    }


}


