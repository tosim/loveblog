package blog.servlet;

import blog.entity.Comment;
import blog.service.CommentServive;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@WebServlet("/GetCommentsServlet")
public class GetCommentsServlet extends AutowiredHttpServlet{
    @Autowired
    CommentServive commentServive;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("art_id = !!!!!!!" + req.getParameter("art_id"));
        System.out.println("！！！！收到getcomments请求");
        int curNum = Integer.parseInt(req.getParameter("curNum"));
        int size = Integer.parseInt(req.getParameter("size"));
        int art_id = Integer.parseInt(req.getParameter("art_id"));
        System.out.println("curNum = " + curNum);
        System.out.println("size = " + size);
        System.out.println("art_id = " + art_id);
        List<Comment> list = commentServive.getCommentsOnArt(art_id,curNum,size);
        if(list.size() <= 0){
            responseJSON(resp,"{\"success\":0}");
            return;
        }
        JSONArray comments = new JSONArray();
        for(Comment comment : list){
            JSONObject jcomment = new JSONObject(comment);
            comments.put(jcomment);
        }
        JSONObject json = new JSONObject();
        json.put("comments",comments);
        json.put("success",1);
//        System.out.println("comments:" + json);
        responseJSON(resp,json.toString());
    }
}
