package blog.dao;

import blog.entity.Article;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
public class TestArticleDao {
    @Autowired
    ArticleDao articleDao;
//    @Test
//    public void testQueryOneFromNum(){
////        Article article = articleDao.queryOneFromNum(0,"姚小城");
//        Article article = articleDao.queryByPrimaryKey(255);
//        System.out.println(article.getArt_id());
//        System.out.println(article.getArt_author());
//        System.out.println(article.getArt_title());
////        article.setArt_id(0);
////        articleDao.insertArticle(article);
//    }
//
////    @Test
//    public void testQueryTotalNum(){
//        System.out.println(articleDao.queryTotalNum("学习笔记"));
//    }
//
////    @Test
//    public void testUpdateLikeById(){
//        articleDao.updateLikeById(255);
//    }
//
////    @Test
//    public void testUpdateViewById(){
//        articleDao.updateLikeById(255);
//    }
//
////    @Test
//    public void testQueryNextOne(){
//        Article article = articleDao.queryNextOne(256);
//        if(article != null){
//            JSONObject json = new JSONObject(article);
//            System.out.println(json);
//        }else{
//            System.out.println("empty");
//        }
//
//    }
////    @Test
//    public void testQueryPreOne(){
//        JSONObject json = new JSONObject(articleDao.queryPreOne(255));
//        System.out.println(json);
//    }
////    @Test
//    public void testQueryTenHotest(){
//        List<Article> list = articleDao.queryTenHotest();
//        System.out.println(list.size());
//    }
//
////    @Test
//    public void testQueryTenNewest(){
//        List<Article> list = articleDao.queryTenNewest();
//        System.out.println(list.size());
//    }
////    @Test
//    public void testQueryPageByPageNum(){
//        List<Article> list = articleDao.queryPageByPageNum(1,20,"学习笔记");
//        System.out.println(list.size());
//    }
}
