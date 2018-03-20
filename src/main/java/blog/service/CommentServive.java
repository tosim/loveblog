package blog.service;

import blog.entity.Comment;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public interface CommentServive {
    public void insertComment(Comment comment);
    public List<Comment> getCommentsOnArt(Integer art_id,Integer curNum,Integer size);
    public Integer getTotalUser(Integer art_id);
    public Integer getTotalComments(Integer art_id);
}
