package blog.dao;

import blog.entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public interface ArticleDao {
    public Article queryByPrimaryKey(Integer id);
    public void insertArticle(Article article);
    public Article queryOneFromNum(Integer num,String author);//找某作者的下一条记录
    public List<Article> queryPageByPageNum(Integer pageNum,Integer pageSize,String pageClass);
    public Integer queryTotalNum(String PageClass);
    public List<Article> queryTenNewest();
    public List<Article> queryTenHotest();
    public Article queryPreOne(Integer id);
    public Article queryNextOne(Integer id);
    public void updateViewById(Integer id);
    public void updateLikeById(Integer id);
}
