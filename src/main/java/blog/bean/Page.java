package blog.bean;

import blog.entity.Article;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class Page {
    int queryPage;
    int pageSize;
    int totalPage;
    int totalNum;
    List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getQueryPage() {
        return queryPage;
    }

    public void setQueryPage(int queryPage) {
        this.queryPage = queryPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
