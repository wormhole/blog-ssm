package xyz.stackoverflow.blog.util;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 分页工具
 */
public class PageParameter {

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
}
