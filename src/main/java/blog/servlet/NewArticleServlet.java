package blog.servlet;

import blog.entity.Article;
import blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/5/4 0004.
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends AutowiredHttpServlet {
    @Autowired
    ArticleService articleService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("author:" + req.getParameter("author"));
        System.out.println("calss:" + req.getParameter("class"));
        System.out.println("music:" + req.getParameter("music"));
        System.out.println("video:" + req.getParameter("video"));
        System.out.println("title:" + req.getParameter("title"));
        System.out.println("tag:" + req.getParameter("tag"));
        System.out.println("tag:" + req.getParameter("img"));
        //System.out.println("music:" + req.getParameter("content"));

        Article article = new Article();
        article.setArt_content(req.getParameter("content"));
        article.setArt_author(req.getParameter("author"));
        article.setArt_class(req.getParameter("class"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        article.setArt_time(sdf.format(new Date()).toString());
        article.setArt_title(req.getParameter("title"));
        article.setArt_tag(req.getParameter("tag"));
        article.setArt_music(req.getParameter("music"));
        article.setArt_video(req.getParameter("video"));
        article.setArt_like(0);
        article.setArt_dislike(0);
        article.setArt_img(req.getParameter("img") == null ? "img/book.jpg":req.getParameter("img"));
        articleService.insertArticle(article);
        responseJSON(resp,"{\"success\":1}");
    }
}
