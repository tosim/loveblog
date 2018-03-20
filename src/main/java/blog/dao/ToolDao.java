package blog.dao;

import blog.entity.Article;
import blog.entity.Tool;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public interface ToolDao {
    public void insertTool(Tool tool);
    public List<Tool> queryPageByPageNum(Integer pageNum, Integer pageSize);
    public Integer queryTotalNum();
    public Tool queryById(int id);
}
