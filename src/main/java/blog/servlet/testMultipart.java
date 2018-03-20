package blog.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@WebServlet("/testMultipart")
@MultipartConfig
public class testMultipart extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("!!size = " + req.getParts().size());
        System.out.println("!!tmp = " + req.getParameter("tmp"));
        Part part = req.getPart("file1");
        for(String headerName : part.getHeaderNames()){
            System.out.println(headerName + " : " + part.getHeader(headerName));
        }
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"success\":\"ok\"}");
    }
}
