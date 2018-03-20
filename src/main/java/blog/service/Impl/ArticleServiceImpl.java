package blog.service.Impl;

import blog.dao.ArticleDao;
import blog.entity.Article;
import blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleDao articleDao;

    @Override
    public Article getArticleById(int id) {
        return articleDao.queryByPrimaryKey(id);
    }

    @Override
    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    @Override
    public Article queryOneFromNum(Integer num, String author) {
        return articleDao.queryOneFromNum(num,author);
    }

    @Override
    public List<Article> getPageByPageNum(Integer pageNum, Integer pageSize,String pageClass) {
        return articleDao.queryPageByPageNum((pageNum-1)*pageSize,pageSize,pageClass);
    }

    @Override
    public Integer getTotalNum(String pageClass) {
        return articleDao.queryTotalNum(pageClass);
    }

    @Override
    public List<Article> getTenNewest() {
        return articleDao.queryTenNewest();
    }

    @Override
    public List<Article> getTenHotest() {
        return articleDao.queryTenHotest();
    }

    @Override
    public Article getPreOne(Integer id) {
        return articleDao.queryPreOne(id);
    }

    @Override
    public Article getNextOne(Integer id) {

        return articleDao.queryNextOne(id);
    }

    @Override
    public void addLikeById(Integer id) {
        articleDao.updateLikeById(id);
        return;
    }

    @Override
    public void addViewById(Integer id) {
        articleDao.updateViewById(id);
        return;
    }
}
