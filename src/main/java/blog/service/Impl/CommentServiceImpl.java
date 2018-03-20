package blog.service.Impl;

import blog.dao.CommentDao;
import blog.entity.Comment;
import blog.service.CommentServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Service("CommentService")
public class CommentServiceImpl implements CommentServive{
    @Autowired
    CommentDao commentDao;
    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public Integer getTotalUser(Integer art_id) {
        return commentDao.selectTotalUser(art_id);
    }

    @Override
    public Integer getTotalComments(Integer art_id) {
        return commentDao.selectTotalComments(art_id);
    }

    @Override
    public List<Comment> getCommentsOnArt(Integer art_id,Integer curNum,Integer size) {

        return commentDao.selectCommentsOnArt(art_id,(curNum-1)*size,size);
    }
}
