package blog.dao.impl;

import blog.dao.ToolDao;
import blog.entity.Article;
import blog.entity.Tool;
import blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@Repository("toolDao")
public class ToolDaoImpl implements ToolDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Tool> toolRowMapper = new BeanPropertyRowMapper<Tool>(Tool.class);
    private static final String baseColumn = " t_id,t_name,t_version,t_owner,t_abstract,t_description,t_download_cnt,t_like,t_time,t_path ";

    @Override
    public Tool queryById(int id) {
        String sql = "select "+baseColumn+" from tool where t_id=?";
        Tool tool = jdbcTemplate.queryForObject(sql,toolRowMapper,id);
        return tool;
    }

    @Override
    public List<Tool> queryPageByPageNum(Integer pageNum, Integer pageSize) {
        String sql = "select" + baseColumn + "from tool order by t_id desc limit ?,?";
        List<Tool> list = jdbcTemplate.query(sql,toolRowMapper,new Object[]{pageNum,pageSize});
        return list;
    }

    @Override
    public Integer queryTotalNum() {
        String sql = "select count(*) from tool";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public void insertTool(Tool tool) {
        String sql = "insert into tool(" + baseColumn +") values(?,?,?,?,?,?,?,?,?,?)";
        List list = new ArrayList();
        list.add(tool.getT_id());
        list.add(tool.getT_name());
        list.add(tool.getT_version());
        list.add(tool.getT_owner());
        list.add(tool.getT_abstract());
        list.add(tool.getT_description());
        list.add(tool.getT_download_cnt());
        list.add(tool.getT_like());
        list.add(tool.getT_time());
        list.add(tool.getT_path());
        jdbcTemplate.update(sql,list.toArray());
    }
}
