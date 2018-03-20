package blog.dao;

import blog.entity.Comment;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface CommentDao {
    public void insertComment(Comment comment);
    public List<Comment> selectCommentsOnArt(Integer art_id,Integer num,Integer size);
    public Integer selectTotalComments(Integer art_id);
    public Integer selectTotalUser(Integer art_id);
}
