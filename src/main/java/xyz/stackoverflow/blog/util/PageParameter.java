package xyz.stackoverflow.blog.util;

/**
 * 分页工具
 *
 * @author 凉衫薄
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
