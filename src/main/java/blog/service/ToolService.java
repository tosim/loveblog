package blog.service;

import blog.entity.Article;
import blog.entity.Tool;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public interface ToolService {
    public void uploadTool(Tool tool);
    public Integer getTotalNum();
    public List<Tool> getPageByPageNum(Integer pageNum, Integer pageSize);
    public Tool getToolById(int id);
}
