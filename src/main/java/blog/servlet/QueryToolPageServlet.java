package blog.servlet;

import blog.bean.Page;
import blog.entity.Article;
import blog.entity.Tool;
import blog.service.ToolService;
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
 * Created by Administrator on 2017/6/13 0013.
 */
@WebServlet("/QueryToolPageServlet")
public class QueryToolPageServlet extends AutowiredHttpServlet{
    @Autowired
    ToolService toolService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("QueryToolPageServlet!");
        int queryPage = Integer.parseInt(req.getParameter("queryPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String pageClass = req.getParameter("pageClass");
        int totalNum = toolService.getTotalNum();
        //System.out.println("total num = "+totalNum);
        int totalPage = totalNum / pageSize + (totalNum%pageSize == 0 ? 0 : 1);
        List<Tool> tools = toolService.getPageByPageNum(queryPage,pageSize);//一页的文章

        Page page = new Page();
        page.setQueryPage(queryPage);
        page.setPageSize(pageSize);
        page.setTotalNum(totalNum);
        page.setTotalPage(totalPage);
        page.setList(tools);

        System.out.println("toos size:" + tools.size());
        JSONObject json = new JSONObject(page);

        System.out.println(json.toString());

        responseJSON(resp,json.toString());
    }
}
