package blog.servlet;

import blog.dao.CommentDao;
import blog.entity.Comment;
import blog.entity.User;
import blog.service.CommentServive;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends AutowiredHttpServlet{
    @Autowired
    CommentServive commentServive;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到新评论的请求");
        User user = (User)req.getSession().getAttribute("user");
        if(null == user){
            System.out.println("user is null");
            JSONObject json = new JSONObject();
            json.put("success",0);
            responseJSON(resp,json.toString());
            return;
        }
        System.out.println("到这里了" + user);
        int art_id = Integer.parseInt(req.getParameter("art_id"));
        String com_content = req.getParameter("com_content");
        Comment comment = new Comment();
        comment.setArt_id(art_id);
        comment.setU_id(user.getU_id());
        comment.setCom_content(com_content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        comment.setCom_time(sdf.format(new Date()).toString());
        comment.setCom_agree(0);
        comment.setCom_disagree(0);
        commentServive.insertComment(comment);
        JSONObject jcomment = new JSONObject(comment);
        jcomment.put("u_name",user.getU_name());
        jcomment.put("u_img",user.getU_img());
        JSONObject juser = new JSONObject(user);
        juser.remove("u_pass");
        JSONObject json = new JSONObject();
        json.put("user",juser);
        json.put("comment",jcomment);
        json.put("success",1);
        System.out.println("data:" + json.toString());
        responseJSON(resp,json.toString());
    }
}
