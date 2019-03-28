package xyz.stackoverflow.blog.pojo.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类实体类
 *
 * @author 凉衫薄
 */
public class CategoryPO implements Serializable {
    private String id;
    private String name;
    private String code;
    private Integer deleteAble;
    private Date date;

    public CategoryPO() {

    }

    public CategoryPO(String id, String name, String code, Integer deleteAble, Date date) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.deleteAble = deleteAble;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
