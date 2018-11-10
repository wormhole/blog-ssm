package xyz.stackoverflow.blog.util.db;

import java.io.Serializable;

/**
 * 分页工具
 *
 * @author 凉衫薄
 */
public class PageParameter implements Serializable {

    private int page;
    private int start;
    private int limit;
    private String where;

    public PageParameter() {

    }

    public PageParameter(int page, int limit, String where) {
        this.page = page;
        this.start = (page - 1) * limit;
        this.limit = limit;
        this.where = where;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
