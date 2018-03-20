package blog.servlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AutowiredHttpServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }

    public void responseJSON(HttpServletResponse response,String json){
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
        } catch (IOException e) {
        }
    }
}
