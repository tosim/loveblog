package blog.servlet;

import blog.bean.Page;
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
import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@WebServlet("/QueryPageServlet")
public class QueryPageServlet extends AutowiredHttpServlet {

    @Autowired
    ArticleService articleService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("QueryPageServlet!");
        int queryPage = Integer.parseInt(req.getParameter("queryPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String pageClass = req.getParameter("pageClass");
        int totalNum = articleService.getTotalNum(pageClass);
        //System.out.println("total num = "+totalNum);
        int totalPage = totalNum / pageSize + (totalNum%pageSize == 0 ? 0 : 1);
        List<Article> articles = articleService.getPageByPageNum(queryPage,pageSize,pageClass);//一页的文章
        for(Article art : articles){
            art.setArt_abstract(StringUtil.Html2Text(art.getArt_content()));
        }
        Page page = new Page();
        page.setQueryPage(queryPage);
        page.setPageSize(pageSize);
        page.setTotalNum(totalNum);
        page.setTotalPage(totalPage);
        page.setList(articles);
        JSONObject json = new JSONObject(page);
        responseJSON(resp,json.toString());
    }
}
