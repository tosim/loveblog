package blog.service.Impl;

import blog.dao.ToolDao;
import blog.entity.Tool;
import blog.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@Service("toolService")
public class ToolServiceImpl implements ToolService{
    @Autowired
    ToolDao toolDao;
    @Override
    public void uploadTool(Tool tool) {
        toolDao.insertTool(tool);
    }

    @Override
    public Integer getTotalNum() {
        return toolDao.queryTotalNum();
    }

    @Override
    public Tool getToolById(int id) {
        return toolDao.queryById(id);
    }

    @Override
    public List<Tool> getPageByPageNum(Integer pageNum, Integer pageSize) {
        return toolDao.queryPageByPageNum((pageNum-1)*pageSize,pageSize);
    }
}
