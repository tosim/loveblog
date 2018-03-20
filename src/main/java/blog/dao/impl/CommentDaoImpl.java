package blog.dao.impl;

import blog.dao.CommentDao;
import blog.entity.Comment;
import blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Repository("CommentDao")
public class CommentDaoImpl implements CommentDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Comment> commentRowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
    private static final String baseColumn = " com_id,comment.art_id,comment.u_id,com_content,com_time,com_agree,com_disagree ";

    @Override
    public List<Comment> selectCommentsOnArt(Integer art_id,Integer num,Integer size) {
        String sql = "select user.u_name,user.u_img," + baseColumn + "from comment,user where art_id=? and comment.u_id=user.u_id order by com_id desc limit ?,?";
//        System.out.println(sql);
        System.out.println("num = " + num);
        List<Comment> list = jdbcTemplate.query(sql,commentRowMapper,new Object[]{art_id,num,size});
        System.out.println("list size:" + list.size());
        return list;
    }

    @Override
    public void insertComment(Comment comment) {
        String sql = "insert into comment("+baseColumn+") values(?,?,?,?,?,?,?)";
        List list = new ArrayList();
        list.add(comment.getCom_id());
        list.add(comment.getArt_id());
        list.add(comment.getU_id());
        list.add(comment.getCom_content());
        list.add(comment.getCom_time());
        list.add(comment.getCom_agree());
        list.add(comment.getCom_disagree());
        jdbcTemplate.update(sql,list.toArray());
        return;
    }

    @Override
    public Integer selectTotalComments(Integer art_id) {
        String sql = "select count(*) from comment where art_id = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,art_id);
    }

    @Override
    public Integer selectTotalUser(Integer art_id) {
        String sql = "select count(*) from (select distinct u_id from comment where art_id=?) as tmp";
        return jdbcTemplate.queryForObject(sql,Integer.class,art_id);
    }
}
