package blog.dao;

import blog.entity.Comment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
public class TestCommentDao {
    @Autowired
    CommentDao commentDao;
//    @Test
//    public void testInsertComment(){
//        Comment comment = new Comment();
//        comment.setArt_id(255);
//        comment.setU_id(1);
//        comment.setCom_content("测试评论255");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        comment.setCom_time(sdf.format(new Date()).toString());
//        comment.setCom_agree(0);
//        comment.setCom_disagree(0);
//        commentDao.insertComment(comment);
//    }
//
////    @Test
//    public void testSelectCommentsOnArt(){
//        List<Comment> list = commentDao.selectCommentsOnArt(260,3,2);
//        JSONArray comments = new JSONArray();
//        for(Comment comment : list){
//            JSONObject jcomment = new JSONObject(comment);
//            System.out.println("comment:" + jcomment);
//            comments.put(jcomment);
//        }
//        JSONObject json = new JSONObject();
//        json.put("comments",comments);
//        json.put("success",1);
////        System.out.println("comments:" + json);
//    }
}
