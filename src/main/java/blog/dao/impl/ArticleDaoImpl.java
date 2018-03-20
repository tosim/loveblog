package blog.dao.impl;

import blog.dao.ArticleDao;
import blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Repository("ArticleDao")
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<Article>(Article.class);

    public static final String baseColumn = " art_id,art_time,art_author, art_title ,art_content,art_tag,art_class,art_music,art_video,art_like,art_dislike,art_img,art_view ";

    @Override
    public Article queryByPrimaryKey(Integer id) {
//        select
//                <include refid="Base_Column_List" />
//                from article
//        where art_id = #{id,jdbcType=INTEGER}
        String sql = "select "+baseColumn+" from article where art_id=?";
//        System.out.println(sql);
        Article article = jdbcTemplate.queryForObject(sql,articleRowMapper,id);
        return article;
    }

    @Override
    public void insertArticle(Article article) {
//        insert into article(<include refid="Base_Column_List"></include>) values(#{art_id},#{art_time},#{art_author},#{art_title},#{art_content},#{art_tag},#{art_class},#{art_music},#{art_video},#{art_like},#{art_dislike},#{art_img},#{art_view})
        String sql = "insert into article("+baseColumn+") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        List list = new ArrayList();
        list.add(article.getArt_id());
        list.add(article.getArt_time());
        list.add(article.getArt_author());
        list.add(article.getArt_title());
        list.add(article.getArt_content());
        list.add(article.getArt_tag());
        list.add(article.getArt_class());
        list.add(article.getArt_music());
        list.add(article.getArt_video());
        list.add(article.getArt_like());
        list.add(article.getArt_dislike());
        list.add(article.getArt_img());
        list.add(article.getArt_view());
        jdbcTemplate.update(sql,list.toArray());
        return;
    }

    @Override
    public Article queryOneFromNum(Integer num, String author) {
//        select baseColumn
//        from article
//        where art_author = author
//        order by art_id desc
//        limit num,1
        String sql = "select " + baseColumn + " from article where art_author=? order by art_id desc limit ?,1";
//        System.out.println(sql);
        Article article = jdbcTemplate.queryForObject(sql,articleRowMapper,new Object[]{author,num});
        return article;
    }

    @Override
    public List<Article> queryPageByPageNum(Integer pageNum, Integer pageSize, String pageClass) {
//        select <include refid="Base_Column_List"></include>
//                from article
//        where art_class=#{2}
//        order by art_id desc
//        limit #{0},#{1}
        String sql = "select" + baseColumn + "from article where art_class=? order by art_id desc limit ?,?";
        List<Article> list = jdbcTemplate.query(sql,articleRowMapper,new Object[]{pageClass,pageNum,pageSize});
        return list;
    }

    @Override
    public Integer queryTotalNum(String pageClass) {
//        select count(*) from article
//        where art_class=#{0}
        String sql = "select count(*) from article where art_class=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,pageClass);
    }

    @Override
    public List<Article> queryTenNewest() {
        String sql = "select" + baseColumn+"from article order by art_id desc limit 10";
        List<Article> list = jdbcTemplate.query(sql,articleRowMapper);
        return list;
    }

    @Override
    public List<Article> queryTenHotest() {
//        select <include refid="Base_Column_List"></include>
//                from article
//        order by art_view desc
//        limit 10;
        String sql = "select" + baseColumn+"from article order by art_view desc limit 10";
        List<Article> list = jdbcTemplate.query(sql,articleRowMapper);
        return list;
    }

    @Override
    public Article queryPreOne(Integer id) {
//        select <include refid="Base_Column_List"></include>
//                from article
//        where art_id=(select max(art_id) from article where art_id&lt;#{0});
        String sql = "select " + baseColumn + " from article where art_id=(select max(art_id) from article where art_id < ?)";
        Article article = null;
        try{
            article = jdbcTemplate.queryForObject(sql,articleRowMapper,id);
        }catch (org.springframework.dao.EmptyResultDataAccessException empty){
        }finally {
            return article;
        }
    }

    @Override
    public Article queryNextOne(Integer id) {
//        select <include refid="Base_Column_List"></include>
//                from article
//        where art_id=(select min(art_id) from article where art_id&gt;#{0});
        String sql = "select " + baseColumn + " from article where art_id=(select min(art_id) from article where art_id>?)";
        Article article = null;
        try{
            article = jdbcTemplate.queryForObject(sql,articleRowMapper,id);
        }catch (org.springframework.dao.EmptyResultDataAccessException empty){
        }finally {
            return article;
        }
//        return article;
    }

    @Override
    public void updateViewById(Integer id) {
        String sql = "update article set art_view=art_view+1 where art_id=?";
        jdbcTemplate.update(sql,id);
        return;
    }

    @Override
    public void updateLikeById(Integer id) {
//        update article set art_like=art_like+1
//        where art_id=#{0};
        String sql = "update article set art_like=art_like+1 where art_id=?";
        jdbcTemplate.update(sql,id);
        return;
    }
}
