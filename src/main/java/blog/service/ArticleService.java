package blog.service;

import blog.entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
public interface ArticleService {
    public Article getArticleById(int id);
    public void insertArticle(Article article);

    public Article queryOneFromNum(Integer num, String author);
    public List<Article> getPageByPageNum(Integer pageNum,Integer pageSize,String pageClass);
    public Integer getTotalNum(String pageClass);
    public List<Article> getTenNewest();
    public List<Article> getTenHotest();
    public Article getPreOne(Integer id);
    public Article getNextOne(Integer id);
    public void addViewById(Integer id);
    public void addLikeById(Integer id);
}
